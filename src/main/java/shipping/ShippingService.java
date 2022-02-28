package shipping;

import java.util.*;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public List<Transportable> getPackages() {
        return new ArrayList<>(packages);
    }

    public void addPackage(Transportable item) {
        if (item != null) {
            packages.add(item);
        } else {
            throw new IllegalArgumentException("Package can not be null.");
        }

    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(i -> i.isBreakable()==breakable)
                .filter(i -> i.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new HashMap<>();

        for (Transportable pack : packages) {
            if (result.containsKey(pack.getDestinationCountry())) {
                result.put(pack.getDestinationCountry(), result.get(pack.getDestinationCountry()) + 1);
            } else {
                result.put(pack.getDestinationCountry(), 1);
            }
        }

        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        List<Transportable> result = new ArrayList<>(packages);
        result = result.stream().filter(i -> !i.getDestinationCountry().equals("Hungary")).toList();
        //result.stream().sorted(Comparator.comparing((InternationalPackage p) -> p.getDistance();
        result.stream().sorted(Comparator.comparing(i -> i.getDistance()));
        return result;
    }
}
