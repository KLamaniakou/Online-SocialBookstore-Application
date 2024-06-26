package myy803.springboot.OnlineBookStore.controller;

import myy803.springboot.OnlineBookStore.dao.BookDAO;
import myy803.springboot.OnlineBookStore.dao.RequestDAO;
import myy803.springboot.OnlineBookStore.dao.UserProfileMapper;
import myy803.springboot.OnlineBookStore.forms.BookData;
import myy803.springboot.OnlineBookStore.forms.UserProfileFormData;
import myy803.springboot.OnlineBookStore.model.Book;
import myy803.springboot.OnlineBookStore.model.UserProfile;
import myy803.springboot.OnlineBookStore.model.bookrequest;
import myy803.springboot.OnlineBookStore.service.BookService;
import myy803.springboot.OnlineBookStore.service.BookServiceImp;
import myy803.springboot.OnlineBookStore.service.RequestService;
import myy803.springboot.OnlineBookStore.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookServiceImp BookService;
    @Autowired
    private BookDAO BookDAO;
    @Autowired
    private RequestService requestService;
    @Autowired
    private UserProfileMapper profileMapper;

    @Autowired
    private RequestDAO RequestDAO;
    @Autowired
    UserProfileService userProfileService;

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @RequestMapping("/book/form")
    public String AddBook(Model model) {
        BookData books = new BookData();
        model.addAttribute("book", books);
        return "Book/bookform";
    }

    @RequestMapping("/book/back")
    public String back() {
        return "user/dashboard";
    }

    @RequestMapping("/saveBook")
    public String saveBook(@ModelAttribute("books") Book book, Model model) {
        String loggedInUsername = getUsername();
        book.setUsername(loggedInUsername);
        BookService.saveUserBook(book);
        return "redirect:user/bookdash";
    }

    @RequestMapping("/user/bookdash")
    public String MyBooks(Model theModel) {
        String loggedInUsername = getUsername();
        List<Book> Books = BookDAO.findByUsername(loggedInUsername);
        theModel.addAttribute("Books", Books);
        return "Book/bookdash";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("BookId") int theId) {
        BookService.deleteById(theId);
        return "redirect:user/bookdash";
    }

    @RequestMapping("/user/searchdash")
    public String searchdash() {
        return "Book/booksearch";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "author", required = false) String author,
                              @RequestParam(name = "searchType", required = false) String searchType, Model model) {
        if (searchType.equals("exact")) {
            List<Book> books = BookDAO.findByTitleAndAuthors(title, author);
            model.addAttribute("books", books);
        } else {
            List<Book> books = BookDAO.findByAuthors(author);
            model.addAttribute("books", books);
        }
        return "Book/searchres";
    }

    @RequestMapping("/requestBook")
    public String requestbook(@ModelAttribute("request") bookrequest bookrequest, Model model, @RequestParam("title") String title, @RequestParam("owner") String owner) {
        String loggedInUsername = getUsername();
        bookrequest.setUsername(loggedInUsername);
        bookrequest.setTitle(title);
        bookrequest.setOwner(owner);
        requestService.save(bookrequest);
        return "user/dashboard";
    }

    @RequestMapping("/recommended")
    public String recommended(Model theModel) {
        String loggedInUsername = getUsername();
        Optional<UserProfile> profileOptional = profileMapper.findByUsername(loggedInUsername);
        if (profileOptional.isPresent()) {
            UserProfile profile = profileOptional.get();
            List<Book> books = BookDAO.findByCategoryOrAuthors(profile.getCategory(), profile.getAuthor());
            theModel.addAttribute("books", books);
            return "Book/searchres";
        }
        return "Book/booksearch";
    }


    @RequestMapping("/Book/RequestsMade")
    public String requestsmade(Model theModel) {
        String loggedInUsername = getUsername();
        List<bookrequest> req = RequestDAO.findByUsername(loggedInUsername);
        theModel.addAttribute("req", req);
        return "Book/maderequest";
    }

    @RequestMapping("/Book/Myrequests")
    public String Myrequests(@RequestParam("title") String title,Model theModel) {
        String loggedInUsername = getUsername();
        List<bookrequest> req = RequestDAO.findByTitleAndOwner(title,loggedInUsername);
        theModel.addAttribute("req", req);
        return "Book/myrequest";
    }

    @RequestMapping("/answer")
    public String answer(@RequestParam("id") int theId) {
        List<bookrequest> req = RequestDAO.findById(theId);
        for (bookrequest request : req) {
            request.setStatus(true);
            request.setAnswer("You can Take the Book");
            requestService.save(request);
        }
        List<bookrequest> allrequests = RequestDAO.findAll();
        for (bookrequest request : allrequests) {
            if (request.getStatus() == null){
                request.setStatus(false);
                request.setAnswer("another user has taken the book");
                requestService.save(request);
            }
        }

        return "redirect:/user/bookdash";
    }
    @RequestMapping("/info")
    public String answer(@RequestParam("user") String user,Model theModel) {
        UserProfileFormData ProfileFormData = new UserProfileFormData();
        UserProfileFormData userProfile = userProfileService.retrieveProfile(user,ProfileFormData);
        theModel.addAttribute("userProfile", userProfile);
        return "Book/requestinfo";
    }
}
