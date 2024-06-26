package myy803.springboot.OnlineBookStore.service;

import java.util.Optional;


import myy803.springboot.OnlineBookStore.dao.UserProfileMapper;
import myy803.springboot.OnlineBookStore.forms.UserProfileFormData;
import myy803.springboot.OnlineBookStore.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserProfileServiceImpl implements UserProfileService {

	 @Autowired
	 private UserProfileMapper userProfileMapper;

	public UserProfileFormData retrieveProfile(String username, UserProfileFormData userProfileFormData) {

		Optional<UserProfile> userProfileOptional = userProfileMapper.findByUsername(username);

		if (userProfileOptional.isPresent()) {
			UserProfile userProfile = userProfileOptional.get();
			userProfileFormData.setFullName(userProfile.getFullName());
			userProfileFormData.setAddress(userProfile.getAddress());
			userProfileFormData.setAge(userProfile.getAge());
			userProfileFormData.setPhoneNumber(userProfile.getPhoneNumber());
			userProfileFormData.setCategory(userProfile.getCategory());
			userProfileFormData.setAuthor(userProfile.getAuthor());

			return userProfileFormData;
		} else {
			return null;
		}
}
	private UserProfile updateProfileData(UserProfile profile, UserProfileFormData formData) {
		profile.setFullName(formData.getFullName());
		profile.setAddress(formData.getAddress());
		profile.setAge(formData.getAge());
		profile.setPhoneNumber(formData.getPhoneNumber());
		profile.setCategory(formData.getCategory());
		profile.setAuthor(formData.getAuthor());
		return profile;
	}
	private UserProfileFormData convertToUserProfileFormData(UserProfile userProfile) {
		UserProfileFormData formData = new UserProfileFormData();
		formData.setFullName(userProfile.getFullName());
		formData.setAddress(userProfile.getAddress());
		formData.setAge(userProfile.getAge());
		formData.setPhoneNumber(userProfile.getPhoneNumber());
		formData.setCategory(userProfile.getCategory());
		formData.setAuthor(userProfile.getAuthor());
		return formData;
	}
	public void save(UserProfileFormData userProfileFormData , UserProfile userProfile) {
		Optional<UserProfile> userProfileOptional = userProfileMapper.findByUsername(userProfile.getUsername());
		if (userProfileOptional.isPresent()) {
			UserProfile existingProfile = userProfileOptional.get();
			updateProfileData(existingProfile, userProfileFormData);
			userProfileMapper.save(updateProfileData(existingProfile, userProfileFormData));
		} else {
			userProfile.setFullName(userProfileFormData.getFullName());
			userProfile.setAddress(userProfileFormData.getAddress());
			userProfile.setAge(userProfileFormData.getAge());
			userProfile.setPhoneNumber(userProfileFormData.getPhoneNumber());
			userProfile.setCategory(userProfileFormData.getCategory());
			userProfile.setAuthor(userProfileFormData.getAuthor());
			userProfileMapper.save(userProfile);
		}
	}
}
