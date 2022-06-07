package tads.eaj.br.produtosmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tads.eaj.br.produtosmvc.model.Produto;
import tads.eaj.br.produtosmvc.service.ProdutoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProdutoController {
    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = {"/", "/produtos"})
    public String getHome(Model model){

        List<Produto> produtos = service.findAll();
        model.addAttribute("produtosList", produtos);

        return "index";
    }

    @RequestMapping("/cadastrar")
    public String getCadastar(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("aula", "hello");

        Produto p = new Produto();
        model.addAttribute("produto", p);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute @Valid Produto p, Errors errors){

        if(errors.hasErrors()){
            return "cadastrar";
        }else{
            service.create(p);
            return "redirect:/";
        }
    }

    @RequestMapping("/deletar/{id}")
    public String doDeletar(@PathVariable (name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/editar/{id}")
    public String doEditar(@PathVariable (name = "id") Long id, Model model){

        Produto p = service.findById(id);
        model.addAttribute("produto", p);
        return "cadastrar";
    }
}







