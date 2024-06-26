package myy803.springboot.OnlineBookStore.service;

import myy803.springboot.OnlineBookStore.forms.UserProfileFormData;
import myy803.springboot.OnlineBookStore.model.UserProfile;
import org.springframework.stereotype.Service;




@Service
public interface UserProfileService {

	public UserProfileFormData retrieveProfile(String username,UserProfileFormData userProfileFormData);

	public void save(UserProfileFormData userProfileFormData, UserProfile userProfile);

}
