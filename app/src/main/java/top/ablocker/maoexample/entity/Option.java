package top.ablocker.maoexample.entity;

import java.util.List;
import java.util.ArrayList;

public class Option
{
    private int id;
    private String content;
    private List<String> TAGList = new ArrayList<>();

    public Option()
    {
    }

    public Option(String content, List<String> tAGList)
    {
        this.content = content;
        TAGList = tAGList;
    }

    public Option(int id, String content, List<String> tAGList)
    {
        this.id = id;
        this.content = content;
        TAGList = tAGList;
    }

    public int getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public List<String> getTAGList()
    {
        return TAGList;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setTAGList(List<String> tAGList)
    {
        TAGList = tAGList;
    }
}
