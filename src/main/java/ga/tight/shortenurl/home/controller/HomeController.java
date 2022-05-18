package ga.tight.shortenurl.home.controller;

import ga.tight.shortenurl.home.dto.RequestRegisterUrlForm;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.service.ShortenRegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private final ShortenRegisterService shortenRegisterService;
    private final ModelMapper modelMapper;

    public HomeController(ShortenRegisterService shortenRegisterService, ModelMapper modelMapper) {
        this.shortenRegisterService = shortenRegisterService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/home/shorten-urls")
    public ModelAndView post(
            HttpServletRequest request,
            RequestRegisterUrlForm form,
            RedirectAttributes re
            )
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");

        if(form.getUrl().equals("")) {
            return mav;
        }

        ShortenUrl shorten = shortenRegisterService.register(modelMapper.map(form, RegisterShortenDto.class));
        ResponseRegisterShortenDto dto = ResponseRegisterShortenDto.of(shorten);
        String baseUrl = request.getRequestURL().toString();
        re.addFlashAttribute("url", dto.mergeUrl(baseUrl));

        return mav;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
