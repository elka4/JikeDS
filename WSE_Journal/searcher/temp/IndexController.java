package edu.nyu.cs.wse.searcher;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController  {

    //extends WebMvcConfigurerAdapter

/*    @RequestMapping("/")
    String index(){
        return "index";
    }*/


    @RequestMapping("/query")
    public String searcher(@RequestParam(value="query2", required=false) String argStr, Model model) {
        System.out.println("words: " + argStr);

//        String[] strArr = name.split("\\s+");
//        Searcher searcher = new Searcher();
//        List<Result> resutls = searcher.search(strArr);
        // http://localhost:8080/query?query2=haha    name: World

        // http://localhost:8080/query?query2=haha    name: haha

        Results resutls = new Results(argStr);

        model.addAttribute("query2", resutls);
//        return "query";
        return "redirect:/";
    }


/*    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }*/

    @GetMapping("/")
    public String showForm(Results results) {

        System.out.println("@GetMapping(\"/\") Results: " + results);
        return "index";
    }


    @PostMapping("/")
    public String checkPersonInfo(@RequestParam(value="query2") String argStr, Model model) {
        System.out.println("argStr: " + argStr);

/*        Results results = new Results(argStr);

        model.addAttribute("query2", results);*/

        return "/query?query2="+argStr;
/*        if (bindingResult.hasErrors()) {
            return "query";
        }
        return "redirect:/query";*/

    }


}


// http://localhost:8080/query?query2=haha    name: World

// http://localhost:8080/query?query2=haha    name: haha




//  http://localhost:8080/searcher?name=User

//  @RequestMapping("/searcher")
//  <p>Get your searcher <a href="/searcher">here</a></p>




//                  return "searcher";
// <p th:text="'Hello, ' + ${name} + '!'" />
