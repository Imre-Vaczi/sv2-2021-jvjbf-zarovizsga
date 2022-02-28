package shipping;

public class InternationalPackage implements Transportable{

    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;
    private final int INTERNATIONAL_TRANSPORT_PRICE = 1200;
    private final int VARIABLE_TRANSPORT_COST = 10;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (isBreakable()) {
            return INTERNATIONAL_TRANSPORT_PRICE * 2 + VARIABLE_TRANSPORT_COST * getDistance();
        } else {
            return INTERNATIONAL_TRANSPORT_PRICE + VARIABLE_TRANSPORT_COST * getDistance();
        }
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}
