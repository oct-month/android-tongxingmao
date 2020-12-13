package top.ablocker.maoexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import top.ablocker.maoexample.dao.ApiUse;
import top.ablocker.maoexample.dao.TagDAO;
import top.ablocker.maoexample.entity.Tag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	private TagDAO tagDAO = new TagDAO(this);

	private LinearLayout lyTags;

	private EditText editTagName;
	private TextView textTag;

	private EditText editTagName1;
	private EditText editTagDescrib1;
	private TextView textNewTag;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_get_tags).setOnClickListener(this);
		lyTags = findViewById(R.id.ly_tags);

		findViewById(R.id.btn_get_an_tag).setOnClickListener(this);
		editTagName = findViewById(R.id.edit_tag_name);
		textTag = findViewById(R.id.text_tag);

		findViewById(R.id.btn_add_tag).setOnClickListener(this);
		editTagName1 = findViewById(R.id.edit_new_tag_name);
		editTagDescrib1 = findViewById(R.id.edit_new_tag_describ);
		textNewTag = findViewById(R.id.text_new_tag);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_get_tags:
				tagDAO.getAllTags(new ApiUse<List<Tag>>() {
					@Override
					public void onSuccess(List<Tag> tagList)
					{
						lyTags.removeAllViews();
						for (Tag tag: tagList)
						{
							TextView textTag = new TextView(MainActivity.this);
							textTag.setText(tag.getName() + " -- " + tag.getDescrib());
							lyTags.addView(textTag);
						}
					}

					@Override
					public void onFail(Exception e)
					{
						e.printStackTrace();
					}
				});
				break;
			case R.id.btn_get_an_tag:
				tagDAO.getAnTag(editTagName.getText().toString(), new ApiUse<Tag>() {
					@Override
					public void onSuccess(Tag tag)
					{
						textTag.setText(tag.getName() + " -- " + tag.getDescrib());
					}

					@Override
					public void onFail(Exception e)
					{
						e.printStackTrace();
					}
				});
				break;
			case R.id.btn_add_tag:
				Tag newTag = new Tag(editTagName1.getText().toString(), editTagDescrib1.getText().toString());
				tagDAO.addAnTag(newTag, new ApiUse<Tag>() {
					@Override
					public void onSuccess(Tag tag)
					{
						textNewTag.setText(tag.getName() + " -- " + tag.getDescrib());
					}

					@Override
					public void onFail(Exception e)
					{
						e.printStackTrace();
					}
				});
				break;
		}
	}
}
