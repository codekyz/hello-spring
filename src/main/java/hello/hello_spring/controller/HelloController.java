package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // view가 있는 상황에서 띄우는것, viewResolver가 동작
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // view를 사용하지 않고 바로 받아서 표시, viewResolver가 동작
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring!!!"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // JSON 객체로 반환, Spring도 기본으로 JSON형태를 취함, HttpMessageConverter가 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
