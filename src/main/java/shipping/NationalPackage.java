package shipping;

public class NationalPackage implements Transportable{

    private int weight;
    private boolean breakable;
    private final int TRANSPORT_PRICE = 1000;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
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
            return TRANSPORT_PRICE * 2;
        } else {
            return TRANSPORT_PRICE;
        }
    }

    @Override
    public String getDestinationCountry() {
        return Transportable.super.getDestinationCountry();
    }

}
