package myy803.springboot.OnlineBookStore.controller;

import myy803.springboot.OnlineBookStore.forms.UserProfileFormData;
import myy803.springboot.OnlineBookStore.model.UserProfile;
import myy803.springboot.OnlineBookStore.service.UserProfileService;
import myy803.springboot.OnlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;

    @RequestMapping("/user/dashboard")
    public String getUserHome(){
        return "user/dashboard";
    }
    @RequestMapping("user/profile")
    public String retrieveProfile(Model model) {
        String username = getUsername();
        UserProfileFormData ProfileFormData = new UserProfileFormData();
        UserProfileFormData userProfile = userProfileService.retrieveProfile(username,ProfileFormData);
        model.addAttribute("userProfile", userProfile);
        return "user/profile";
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @RequestMapping("/user/form")
    public String showForm(Model model) {
        UserProfileFormData userProfileFormData = new UserProfileFormData();
        model.addAttribute("userprofile", userProfileFormData);
        return "user/form";
    }

    @RequestMapping("/saveProfile")
    public String saveProfile(@ModelAttribute("userprofile") UserProfileFormData userProfileFormData, Model theModel) {
        String loggedInUsername = getUsername();
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(loggedInUsername);
        userProfileService.save(userProfileFormData,userProfile);
        theModel.addAttribute("successMessage", "UserProfile made successfully!");
        return "redirect:/user/profile";
    }
}
