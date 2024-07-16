package api;

import api.model.Person;
import api.model.dto.CreatePersonRequestBody;
import api.model.dto.UpdatePersonRequestBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import okhttp3.*;

public class API {

  public static final String version = "0.0.1";

  private static API instance;

  private String apiKey;

  private String domain;

  private int port = 0;

  private OkHttpClient httpClient;

  private API() {
    this.httpClient = new OkHttpClient();
  }

  public static API getInstance() {
    if (instance == null) {
      instance = new API();
    }

    return instance;
  }

  public API domain(String domain) {
    this.domain = domain;
    return getInstance();
  }

  public API apiKey(String apiKey) {
    this.apiKey = apiKey;
    return getInstance();
  }

  public String getApiKey() {
    return apiKey;
  }

  public API port(int port) {
    this.port = port;
    return getInstance();
  }

  public String getDomain() {
    return domain;
  }

  public int getPort() {
    return port;
  }

  public String getUrl() {
    String domain = this.domain;
    if (this.port > 0) {
      domain += ":" + port;
    }

    return domain;
  }

  public OkHttpClient getHttpClient() {
    return httpClient;
  }

  public void setHttpClient(OkHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * Creates a new Person
   **/
  public Person createPerson(CreatePersonRequestBody body) throws Exception {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .post(
        RequestBody.create(
          gson.toJson(body),
          MediaType.parse("application/json")
        )
      )
      .build();

    Response httpResponse = this.httpClient.newCall(request).execute();

    int statusCode = httpResponse.code();
    String response = httpResponse.body().string();

    if (statusCode < 200 || statusCode >= 300) {
      throw new HttpException(statusCode, response);
    }

    return gson.fromJson(response, Person.class);
  }

  /**
   * Delete an existing Person
   **/
  public Person deletePerson(String _id) throws Exception {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + _id + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .delete()
      .build();

    Response httpResponse = this.httpClient.newCall(request).execute();

    int statusCode = httpResponse.code();
    String response = httpResponse.body().string();

    if (statusCode < 200 || statusCode >= 300) {
      throw new HttpException(statusCode, response);
    }

    return gson.fromJson(response, Person.class);
  }

  /**
   * Get all People
   **/
  public ArrayList<Person> getAllPeople() throws Exception {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/persons/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .get()
      .build();

    Response httpResponse = this.httpClient.newCall(request).execute();

    int statusCode = httpResponse.code();
    String response = httpResponse.body().string();

    if (statusCode < 200 || statusCode >= 300) {
      throw new HttpException(statusCode, response);
    }

    Type type = new TypeToken<ArrayList<Person>>() {}.getType();
    return gson.fromJson(response, type);
  }

  /**
   * Get a Person by _id
   **/
  public Person getPerson(String _id) throws Exception {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + _id + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .get()
      .build();

    Response httpResponse = this.httpClient.newCall(request).execute();

    int statusCode = httpResponse.code();
    String response = httpResponse.body().string();

    if (statusCode < 200 || statusCode >= 300) {
      throw new HttpException(statusCode, response);
    }

    return gson.fromJson(response, Person.class);
  }

  /**
   * Update an existing Person
   **/
  public Person updatePerson(UpdatePersonRequestBody body) throws Exception {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + body.getId() + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .put(
        RequestBody.create(
          gson.toJson(body),
          MediaType.parse("application/json")
        )
      )
      .build();

    Response httpResponse = this.httpClient.newCall(request).execute();

    int statusCode = httpResponse.code();
    String response = httpResponse.body().string();

    if (statusCode < 200 || statusCode >= 300) {
      throw new HttpException(statusCode, response);
    }

    return gson.fromJson(response, Person.class);
  }

  /**
   * Creates a new Person
   **/
  public void createPerson(
    CreatePersonRequestBody body,
    AsyncCallback<Person> callback
  ) {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .post(
        RequestBody.create(
          gson.toJson(body),
          MediaType.parse("application/json")
        )
      )
      .build();

    this.httpClient.newCall(request)
      .enqueue(
        new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
            callback.onError(e);
          }

          @Override
          public void onResponse(Call call, Response httpResponse)
            throws IOException {
            int statusCode = httpResponse.code();
            String response = httpResponse.body().string();

            if (statusCode < 200 || statusCode >= 300) {
              callback.onError(new HttpException(statusCode, response));
            }

            callback.onSuccess(gson.fromJson(response, Person.class));
          }
        }
      );
  }

  /**
   * Delete an existing Person
   **/
  public void deletePerson(String _id, AsyncCallback<Person> callback) {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + _id + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .delete()
      .build();

    this.httpClient.newCall(request)
      .enqueue(
        new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
            callback.onError(e);
          }

          @Override
          public void onResponse(Call call, Response httpResponse)
            throws IOException {
            int statusCode = httpResponse.code();
            String response = httpResponse.body().string();

            if (statusCode < 200 || statusCode >= 300) {
              callback.onError(new HttpException(statusCode, response));
            }

            callback.onSuccess(gson.fromJson(response, Person.class));
          }
        }
      );
  }

  /**
   * Get all People
   **/
  public void getAllPeople(AsyncCallback<ArrayList<Person>> callback) {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/persons/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .get()
      .build();

    this.httpClient.newCall(request)
      .enqueue(
        new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
            callback.onError(e);
          }

          @Override
          public void onResponse(Call call, Response httpResponse)
            throws IOException {
            int statusCode = httpResponse.code();
            String response = httpResponse.body().string();

            if (statusCode < 200 || statusCode >= 300) {
              callback.onError(new HttpException(statusCode, response));
            }

            Type type = new TypeToken<ArrayList<Person>>() {}.getType();
            callback.onSuccess(gson.fromJson(response, type));
          }
        }
      );
  }

  /**
   * Get a Person by _id
   **/
  public void getPerson(String _id, AsyncCallback<Person> callback) {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + _id + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .get()
      .build();

    this.httpClient.newCall(request)
      .enqueue(
        new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
            callback.onError(e);
          }

          @Override
          public void onResponse(Call call, Response httpResponse)
            throws IOException {
            int statusCode = httpResponse.code();
            String response = httpResponse.body().string();

            if (statusCode < 200 || statusCode >= 300) {
              callback.onError(new HttpException(statusCode, response));
            }

            callback.onSuccess(gson.fromJson(response, Person.class));
          }
        }
      );
  }

  /**
   * Update an existing Person
   **/
  public void updatePerson(
    UpdatePersonRequestBody body,
    AsyncCallback<Person> callback
  ) {
    Gson gson = JsonSerializer.getInstance().getGson();

    Request request = new Request.Builder()
      .url(getUrl() + "/person/" + body.getId() + "/")
      .header("Content-Type", "application/json")
      .header("x-api-key", apiKey)
      .put(
        RequestBody.create(
          gson.toJson(body),
          MediaType.parse("application/json")
        )
      )
      .build();

    this.httpClient.newCall(request)
      .enqueue(
        new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
            callback.onError(e);
          }

          @Override
          public void onResponse(Call call, Response httpResponse)
            throws IOException {
            int statusCode = httpResponse.code();
            String response = httpResponse.body().string();

            if (statusCode < 200 || statusCode >= 300) {
              callback.onError(new HttpException(statusCode, response));
            }

            callback.onSuccess(gson.fromJson(response, Person.class));
          }
        }
      );
  }

  public interface AsyncCallback<T> {
    public void onSuccess(T response);

    public void onError(Exception error);
  }
}
