package dat.backend.model.entities;

public class Cupcake
{

    private Top top;
    private Bottom bottom;
    private int quantity;


    public Cupcake(Top top, Bottom bottom, int quantity)
    {
        this.top = top;
        this.bottom = bottom;
        this.quantity = quantity;
    }

    public Top getTop()
    {
        return top;
    }

    public Bottom getBottom()
    {
        return bottom;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
