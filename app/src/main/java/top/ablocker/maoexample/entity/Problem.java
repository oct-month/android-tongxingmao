package top.ablocker.maoexample.entity;

import java.util.List;
import java.util.ArrayList;

public class Problem
{
    private int id;
    private String title;
    private String content;
    private List<Integer> optionList = new ArrayList<>();

    public Problem()
    {
    }

    public Problem(String title, String content, List<Integer> optionList)
    {
        this.title = title;
        this.content = content;
        this.optionList = optionList;
    }

    public Problem(int id, String title, String content, List<Integer> optionList)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.optionList = optionList;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public List<Integer> getOptionList()
    {
        return optionList;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setOptionList(List<Integer> optionList)
    {
        this.optionList = optionList;
    }
}
