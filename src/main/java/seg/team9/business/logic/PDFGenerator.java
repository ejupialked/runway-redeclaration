package seg.team9.business.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.PrimaryWindowController;
import seg.team9.controllers.airport.AirportViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;
import seg.team9.utils.MockData;

import javax.imageio.ImageIO;


public class PDFGenerator {

    /** Path to the resulting PDF and png files. */
    public static final String PDF = "src/main/resources/printer/calculationReport.pdf";
    public static final String SIDE_PNG = "src/main/resources/printer/sideView.png";
    public static final String TOP_PNG = "src/main/resources/printer/topView.png";

    Font headerFont = FontFactory.getFont("Arial", 18, Font.BOLD);
    Font subTitleFont = FontFactory.getFont("Arial", 14, Font.BOLD);
    Font subTitleItalic = FontFactory.getFont("Arial", 12, Font.BOLDITALIC);
    Font subTitleFontSmall = FontFactory.getFont("Arial", 10, Font.BOLD);
    Font bodyFont = FontFactory.getFont("Arial", 12, Font.NORMAL);
    Font resultFont = FontFactory.getFont("Arial", 12, Font.NORMAL, BaseColor.BLUE );
    public static String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    Document document;

    public PDFGenerator(Airport a, Obstacle o, Runway r) throws IOException, DocumentException {
        takeSnapShots();
        createPdf(a, o, r);
    }

    private void takeSnapShots() {
        WritableImage img1 =
                TopDownViewController
                        .getInstance()
                        .getTopDownView()
                        .snapshot(new SnapshotParameters(), null);
        WritableImage img2 =
                SideViewController
                        .getInstance()
                        .getSideView()
                        .snapshot(new SnapshotParameters(), null);


        File file1 = new File(SIDE_PNG);
        File file2 = new File(TOP_PNG);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(img1, null), "png", file1);
            ImageIO.write(SwingFXUtils.fromFXImage(img2, null), "png", file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createPdf(Airport a, Obstacle o, Runway r) throws DocumentException, IOException {

        this.document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(PDF)));
        document.open();

        PdfPTable header = new PdfPTable(2);
        header.setHorizontalAlignment(Element.ALIGN_LEFT);
        header.setSpacingBefore(10);
        header.completeRow();
        header.setWidths(new int[] { 140, 25 });
        header.setSpacingAfter(10);
        header.getDefaultCell().setBorder(0);
        header.addCell(new Paragraph("Calculation report", headerFont));
        header.addCell(new Paragraph(date, subTitleFontSmall));
        document.add(header);

        PdfPTable details = new PdfPTable(2);
        details.setHorizontalAlignment( 0);
        details.setSpacingBefore(10);
        details.setSpacingAfter(10);
        details.getDefaultCell().setBorder(0);
        details.setWidths(new int[] { 2, 6 });
        PdfPCell title = new PdfPCell(new Phrase("Airport details", subTitleFont));
        title.setColspan(2);
        title.setPaddingBottom(10);
        title.setBorder(0);
        details.addCell(title);
        details.addCell(new Phrase("Name:", subTitleFontSmall));
        details.addCell(a.getName());
        details.addCell(new Phrase("Runway:", subTitleFontSmall));
        details.addCell(a.getRunwayList().get(0).toString());
        details.addCell(new Phrase("Blast protection:", subTitleFontSmall));
        details.addCell("-");
        details.addCell(new Phrase("Strip end:", subTitleFontSmall));
        details.addCell("-");
        PdfPTable obstacle = new PdfPTable(2);
        obstacle.setHorizontalAlignment( 0);
        obstacle.setSpacingBefore(10);
        obstacle.setSpacingAfter(10);
        obstacle.getDefaultCell().setBorder(0);
        obstacle.setWidths(new int[] { 8, 5 });
        PdfPCell titleObstacle = new PdfPCell(new Phrase("Obstacle details", subTitleFont));
        titleObstacle.setColspan(2);
        titleObstacle.setPaddingBottom(10);
        titleObstacle.setBorder(0);
        obstacle.addCell(titleObstacle);
        obstacle.addCell(new Phrase("Name:", subTitleFontSmall));
        obstacle.addCell(o.getName());
        obstacle.addCell(new Phrase("Width:", subTitleFontSmall));
        obstacle.addCell(o.getWidth().toString()+ "m");
        obstacle.addCell(new Phrase("Height:", subTitleFontSmall));
        obstacle.addCell(o.getHeight().toString()+ "m");
        obstacle.addCell(new Phrase("(Distance) right threshold:", subTitleFontSmall));
        obstacle.addCell(o.getDistanceRThreshold().toString()+ "m");
        obstacle.addCell(new Phrase("(Distance) left threshold:", subTitleFontSmall));
        obstacle.addCell(o.getDistanceLThreshold().toString()+ "m");
        obstacle.addCell(new Phrase("(Distance) center", subTitleFontSmall));
        obstacle.addCell(o.getDistanceCenter().toString() + "m");


        PdfPTable outer = new PdfPTable(2);
        outer.setHorizontalAlignment( Element.ALIGN_LEFT);
        outer.setSpacingBefore(10);
        outer.setSpacingAfter(10);
        outer.getDefaultCell().setBorder(0);
        outer.addCell(details);
        outer.addCell(obstacle);
        document.add(outer);


        document.add(new Paragraph("Re-declaration breakdown (to fix with real values...)", subTitleFont));
        addNewlines(document, 1);
        document.add(new Paragraph(new Phrase("09L (Take Off Away, Landing Over):", subTitleItalic)));
        document.add(breakdownTable(r.getLRunway()));
        document.add(new Paragraph(new Phrase("29R (Take Off Away, Landing Over):", subTitleItalic)));
        document.add(breakdownTable(r.getRRunway()));


        PdfPTable table = createDeclarationTable(r);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        document.add(table);


        addNewlines(document, 3);


        document.add(new Paragraph("Visualisation of re-declared distances", subTitleFont));

        addNewlines(document, 1);

        PdfPTable t = new PdfPTable(2);
        t.getDefaultCell().setBorder(0);
        t.addCell(createImageCell(SIDE_PNG));
        t.addCell(createImageCell(TOP_PNG));

        document.add(t);

        document.close();
    }


    public void addNewlines(Document document, int lines)
            throws DocumentException {
        for (int i = 0; i < lines; i++) {
            document.add(Chunk.NEWLINE);
        }
    }



    private PdfPTable breakdownTable(DirectedRunway runway) throws DocumentException {
        PdfPTable breakdown1 = new PdfPTable(3);
        breakdown1.setHorizontalAlignment(0);
        breakdown1.setSpacingBefore(10);
        breakdown1.setSpacingAfter(10);
        breakdown1.getDefaultCell().setBorder(0);
        breakdown1.setWidths(new float[] { 1.2f,0.4f, 12});

        breakdown1.addCell(new Phrase("TORA", subTitleFontSmall));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("Original TORA - Blast Protection - Distance from Threshold - Displaced Threshold", bodyFont));
        breakdown1.addCell((""));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("3902 - 300 - -50 - 306", bodyFont));
        breakdown1.addCell((""));
        breakdown1.addCell("=");
        breakdown1.addCell(new Phrase("2343", resultFont));

        PdfPCell line = new PdfPCell(new Phrase(" "));
        line.getPhrase().getFont().setSize(4);
        line.setBorderColor(BaseColor.WHITE);
        line.setColspan(3);
        breakdown1.addCell(line);

        breakdown1.addCell(new Phrase("ASDA", subTitleFontSmall));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("(R) TORA + STOPWAY", bodyFont));
        breakdown1.addCell(new Phrase("", bodyFont));
        breakdown1.addCell(new Phrase("="));
        breakdown1.addCell(new Phrase("2343", resultFont));

        breakdown1.addCell(line);


        breakdown1.addCell(new Phrase("TODA", subTitleFontSmall));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("(R) TORA + CLEARWAY", bodyFont));
        breakdown1.addCell("");
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("2343", resultFont));

        breakdown1.addCell(line);

        breakdown1.addCell(new Phrase("LDA", subTitleFontSmall));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("Original LDA - Distance from Threshold – Strip End - Slope Calculation",bodyFont));
        breakdown1.addCell((""));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("3595 - -50 - 60 - 12*50", bodyFont));
        breakdown1.addCell((""));
        breakdown1.addCell(new Phrase("=", bodyFont));
        breakdown1.addCell(new Phrase("2343", resultFont));

        return breakdown1;
    }

    public static PdfPTable createDeclarationTable(Runway runway) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new float[] { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f });

        PdfPCell runwayDes = cellWithPadding("Runway designator");
        runwayDes.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(runwayDes);

        PdfPCell tora = cellWithPadding("TORA");
        tora.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(tora);

        PdfPCell toda = cellWithPadding("TODA");
        toda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(toda);

        PdfPCell asda = cellWithPadding("ASDA");
        asda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(asda);

        PdfPCell lda = cellWithPadding("LDA");
        lda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(lda);



        PdfPCell original = new PdfPCell(new Phrase("Original Values"));
        original.setColspan(5);
        original.setBackgroundColor(BaseColor.ORANGE);
        original.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(original);


        PdfPCell desiL = cellWithPadding(runway.getLRunway().getDesignator());
        desiL.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(desiL);
        table.addCell(cellWithPadding(runway.getLRunway().getTora().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getToda().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getAsda().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getLda().toString()+ "m"));

        PdfPCell desiR = cellWithPadding(runway.getRRunway().getDesignator() );
        desiL.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(desiR);
        table.addCell(cellWithPadding(runway.getRRunway().getTora().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getToda().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getAsda().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getLda().toString()+ "m"));

        PdfPCell recalculated = new PdfPCell(new Phrase("Recalculated Values"));
        recalculated.setColspan(5);
        recalculated.setBackgroundColor(BaseColor.ORANGE);
        recalculated.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(recalculated);

        PdfPCell desiL2 = cellWithPadding(runway.getLRunway().getDesignator());
        desiL.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(desiL2);
        table.addCell(cellWithPadding(runway.getLRunway().getWorkingTORA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getWorkingTODA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getWorkingASDA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getLRunway().getWorkingLDA().toString()+ "m"));

        PdfPCell desiR2 = cellWithPadding(runway.getRRunway().getDesignator() );
        desiL.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(desiR2);
        table.addCell(cellWithPadding(runway.getRRunway().getWorkingTORA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getWorkingTODA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getWorkingASDA().toString()+ "m"));
        table.addCell(cellWithPadding(runway.getRRunway().getWorkingLDA().toString()+ "m"));

        return table;
    }

    public PdfPCell createImageCell(String path) throws DocumentException, IOException {
        int indentation = 0;
        Image img = createImage(path);
        img.setAlignment(Element.ALIGN_CENTER);
        img.scaleAbsolute(img.getPlainWidth()*0.2f, img.getPlainHeight()*0.2f);
        PdfPCell cell = new PdfPCell(img, false);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public static PdfPCell createTextCell(String text) {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }


    public static PdfPCell cellWithPadding(String text){
        Phrase p = new Phrase(text);
        Font bold  = new Font(FontFamily.UNDEFINED,12, Font.BOLD);
        p.setFont(bold);
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(10);
        return cell;
    }

    private Image createImage(String path) throws IOException, BadElementException {
        return Image.getInstance(path);
    }
}