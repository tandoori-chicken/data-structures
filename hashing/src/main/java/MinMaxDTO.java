/**
 * Used to calculate min and max horizontal distance from root
 */
public class MinMaxDTO {
    public int min=0;
    public int max=0;

    public MinMaxDTO(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MinMaxDTO{");
        sb.append("min=").append(min);
        sb.append(", max=").append(max);
        sb.append('}');
        return sb.toString();
    }
}
