package com.jscorp.webots.controllers.sociallinkcontroller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jscorp.webots.services.VKUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/vk")
public class VkController {
    private RestTemplate restTemplate;
    private VKUserService vkUserService;
    @Autowired
    public void setVkUserService(VKUserService vkUserService) {
        this.vkUserService = vkUserService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("7430646")
    private String clientId;

    @Value("ekGpA32ix03Qix5Og7hz")
    private String clientSecret;

    @GetMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam(name = "code") String code) {
        String requestUrl = "https://oauth.vk.com/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=http://localhost:8189/webots.ru/vk/callback&code=" + code;
        ResponseEntity<String> accessTokenEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(accessTokenEntity.getBody()).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();

        String userIds = jsonObject.get("user_id").toString();
        System.out.println(userIds);
//        String getFriendsRequest = "https://api.vk.com/method/friends.get?user_ids=" + userIds + "&access_token=" + accessToken + "&v=5.103";
    //  String getNameRequest = "https://api.vk.com/method/users.get?user_ids=" + userIds + "&access_token=" + accessToken + "&fields=last_name&v=5.103";
      //+ "&fields=photo_200&v=5.103"
       String getNameRequest = "https://api.vk.com/method/users.get?user_ids=" + userIds + "&access_token=" + accessToken + "&fields=last_name&v=5.103" + "&fields=city&v=5.103";
        String getBirthDate = "https://api.vk.com/method/users.get?user_ids=" + userIds + "&access_token=" + accessToken + "&fields=bdate&v=5.103" ;
       // String getProfileInfo = "https://api.vk.com/method/account.getProfileInfo?user_ids=" + userIds + "&access_token=" + accessToken + "&fields=sex&v=5.103";
        //+ "&fields=is_closed&v=5.103"
        //account.getProfileInfo
        ResponseEntity<String> userInfoEntity = restTemplate.exchange(getNameRequest, HttpMethod.GET, null, String.class);
        System.out.println(userInfoEntity.getBody());
        ResponseEntity<String> birthdateInfoEntity = restTemplate.exchange(getBirthDate, HttpMethod.GET, null, String.class);
     //  ResponseEntity<String> profileInfoEntity = restTemplate.exchange(getProfileInfo, HttpMethod.GET, null, String.class);
    //   System.out.println(profileInfoEntity.getBody());
        JsonObject jsonUserInfo = parser.parse(userInfoEntity.getBody()).getAsJsonObject().get("response").getAsJsonArray().get(0).getAsJsonObject();
       JsonObject jsonBirthDateInfo = parser.parse(birthdateInfoEntity.getBody()).getAsJsonObject().get("response").getAsJsonArray().get(0).getAsJsonObject();
    //    JsonObject jsonProfileInfo = parser.parse(profileInfoEntity.getBody()).getAsJsonObject().get("response").getAsJsonArray().get(0).getAsJsonObject();
    //    String result = restTemplate.getForObject(getProfileInfo, String.class);

    //   System.out.println(result);
/*
        String bdateFromResponse = jsonBirthDateInfo.get("bdate").toString();
        String[] partsBdate= bdateFromResponse.split("\"");
        String bdate= partsBdate[1];
        System.out.println(bdate);
        String cityArray = jsonUserInfo.get("city").toString();
       String[] partsArray = cityArray.split(",");
        String cityDraft = partsArray[1];
        String[] parts = cityDraft.split(":");
        String cityExtraction = parts[1];
        String[] finalArray= cityExtraction.split("\"");
        String city= finalArray[1];
        System.out.println(city);
        System.out.println(bdate);

        String firstName = jsonUserInfo.get("first_name").getAsString();
        String lastName = jsonUserInfo.get("last_name").getAsString();

        VKUserInfo vkUserInfo = new VKUserInfo();
        if (vkUserService.isUserExist(userIds)==false) {
            vkUserInfo.setVkKuid(userIds);
            vkUserInfo.setFirstname(firstName);
            vkUserInfo.setLastname(lastName);
            vkUserInfo.setBirthdate(bdate);
            //  vkUserInfo.setGender("M");
            vkUserInfo.setCity(city);
            vkUserService.save(vkUserInfo);
        }
 */

     //return "Вы зашли как: " + jsonUserInfo.get("last_name").getAsString() + " " + jsonUserInfo.get("first_name").getAsString();
  return "Вы зашли как: " + jsonUserInfo.get("last_name").getAsString() + " " + jsonUserInfo.get("first_name").getAsString() + " город: " + jsonUserInfo.get("city");
    // return "Фото: "+ "&fields=photo_200&v=5.103";
       // return "Фото: "+ jsonUserInfo.get("photo_200");
        //return "Дата Вашего рождения: "+ jsonUserInfo.get("bdate") + jsonUserInfo.get("photo_200")+ " " + jsonUserInfo.get("is_closed").getAsString();

    }

       @GetMapping("/")
    public String showVkPage(Model model) {
        model.addAttribute("oauthUrl", "https://oauth.vk.com/authorize?client_id=" + clientId + "&display=page&redirect_uri=http://localhost:8189/webots.ru/vk/callback&scope=friends&response_type=code&v=5.103");
        return "vk-demo";
    }

    }
