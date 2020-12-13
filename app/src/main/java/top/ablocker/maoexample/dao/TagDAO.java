package top.ablocker.maoexample.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.ablocker.maoexample.entity.Tag;

public class TagDAO
{
	private OkHttpClient client = new OkHttpClient();
	private Gson gson = new Gson();

	// 获取所有Tag
	public void getAllTags(ApiUse<List<Tag>> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/tagapi/tags")
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					List<Tag> tagList = gson.fromJson(responseJson, new TypeToken<List<Tag>>(){}.getType());
					apiUse.onSuccess(tagList);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
				}
			}
		}).start();
	}

	// 获取一个Tag
	public void getAnTag(String tagName, ApiUse<Tag> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/tagapi/tag/" + tagName)
							.get()
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					Tag tag = gson.fromJson(responseJson, Tag.class);
					apiUse.onSuccess(tag);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
				}
			}
		}).start();
	}

	// 增加一个Tag
	public void addAnTag(Tag tag, ApiUse<Tag> apiUse)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					RequestBody requestBody = RequestBody.create(gson.toJson(tag), ApiConfig.JSON);
					Request request = new Request.Builder()
							.url(ApiConfig.BASE_URL + "/tagapi/add")
							.post(requestBody)
							.build();
					Response response = client.newCall(request).execute();
					String responseJson = response.body().string();
					if (responseJson.trim().equals(""))
					{
						apiUse.onFail();
						return;
					}
					Tag tag1 = gson.fromJson(responseJson, Tag.class);
					apiUse.onSuccess(tag1);
				}
				catch (Exception e) {
					e.printStackTrace();
					apiUse.onFail();
				}
			}
		}).start();
	}
}
