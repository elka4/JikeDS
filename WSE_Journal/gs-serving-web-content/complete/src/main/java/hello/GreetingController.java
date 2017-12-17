package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}

//  http://localhost:8080/greeting?name=User

//  @RequestMapping("/greeting")
//  <p>Get your greeting <a href="/greeting">here</a></p>




//                  return "greeting";
// <p th:text="'Hello, ' + ${name} + '!'" />


