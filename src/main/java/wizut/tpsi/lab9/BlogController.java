package wizut.tpsi.lab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private  BlogRepository blogRepository;

    @GetMapping("/")
    public String homeAction(Model model) throws SQLException{
        List<BlogPost> posts = blogRepository.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @PostMapping("/newpost")
    public String newPostAction(BlogPost blogPost) throws SQLException {
        blogRepository.createPost(blogPost);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePostAction(@PathVariable Integer id) throws SQLException {
        blogRepository.deletePost(id);
        return "redirect:/";
    }
}
