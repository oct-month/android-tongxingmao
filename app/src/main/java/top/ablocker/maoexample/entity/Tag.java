package top.ablocker.maoexample.entity;

public class Tag
{
    private String name;
    private String describ;

    public Tag()
    {
    }

    public Tag(String name, String describ)
    {
        this.name = name;
        this.describ = describ;
    }

    public String getName()
    {
        return name;
    }

    public String getDescrib()
    {
        return describ;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescrib(String describ)
    {
        this.describ = describ;
    }
}
