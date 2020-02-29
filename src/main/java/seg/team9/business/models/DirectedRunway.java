package seg.team9.business.models;

public class DirectedRunway
{
    private String designator;
    private Double toda,tora,asda,lda,resa,als,tocs;
    private Double workingTODA,workingTORA,workingASDA,workingLDA;
    private Double threshold;
    private Double clearway, stopway;

    public DirectedRunway (String designator, Double TODA, Double TORA, Double ASDA, Double LDA, Double threshold)
    {
        this.designator = designator;
        this.threshold = threshold;
        this.toda = TODA;
        this.workingTODA = TODA;
        this.tora = TORA;
        this.workingTORA = TORA;
        this.asda = ASDA;
        this.workingASDA = ASDA;
        this.lda = LDA;
        this.workingLDA = LDA;
    }

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public Double getToda() {
        return toda;
    }

    public void setToda(Double toda) {
        this.toda = toda;
    }

    public Double getWorkingTODA() { return workingTODA; }

    public void setWorkingTODA(Double workingTODA){ this.workingTODA = workingTODA; }

    public Double getTora() { return tora; }

    public void setTora(Double tora) {
        this.tora = tora;
    }

    public Double getWorkingTORA() { return workingTORA; }

    public void setWorkingTORA(Double workingTORA){ this.workingTODA = workingTORA; }

    public Double getAsda() {
        return asda;
    }

    public void setAsda(Double asda) {
        this.asda = asda;
    }

    public Double getWorkingASDA() { return workingASDA; }

    public void setWorkingASDA(Double workingASDA){ this.workingASDA = workingASDA; }

    public Double getLda() {
        return lda;
    }

    public void setLda(Double lda) {
        this.lda = lda;
    }

    public Double getWorkingLDA() { return workingLDA; }

    public void setWorkingLDA(Double workingLDA){ this.workingLDA = workingLDA; }

    public Double getResa() {
        return resa;
    }

    public void setResa(Double resa) {
        this.resa = resa;
    }

    public Double getAls() {
        return als;
    }

    public void setAls(Double als) {
        this.als = als;
    }

    public Double getTocs() {
        return tocs;
    }

    public void setTocs(Double tocs) {
        this.tocs = tocs;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Double getClearway() {
        return clearway;
    }

    public void setClearway(Double clearway) {
        this.clearway = clearway;
    }

    public Double getStopway() {
        return stopway;
    }

    public void setStopway(Double stopway) {
        this.stopway = stopway;
    }
}
