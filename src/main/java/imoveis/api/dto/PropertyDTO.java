package imoveis.api.dto;

public class PropertyDTO {
    private String address;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfPossibleCars;
    private double landSize;
    private int addressNumber;
    private Long consultantId;
    private Double salePrice;
    private Double rentalPrice;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfPossibleCars() {
        return numberOfPossibleCars;
    }

    public void setNumberOfPossibleCars(int numberOfPossibleCars) {
        this.numberOfPossibleCars = numberOfPossibleCars;
    }

    public double getLandSize() {
        return landSize;
    }

    public void setLandSize(double landSize) {
        this.landSize = landSize;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long consultantID) {
        this.consultantId = consultantID;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
