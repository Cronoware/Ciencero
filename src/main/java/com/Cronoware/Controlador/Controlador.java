package com.Cronoware.Controlador;

import com.Cronoware.Mapeo.*;
import com.Cronoware.Modelo.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jaime
 */
@Controller 
public class Controlador{
	
	@Autowired
	UsuarioDAO usuario_db;

	@RequestMapping(value="/")
	public String inicio(){
		return "inicio";
	}
        
        @RequestMapping(value="/getIniciarSesion", method = RequestMethod.GET)
        public ModelAndView getIniciarSesion(ModelMap model, HttpServletRequest request){
            return new ModelAndView("iniciarSesion", model);
        }
        
        @RequestMapping(value="/getRegistro", method = RequestMethod.GET)
        public ModelAndView getRegistro(ModelMap model, HttpServletRequest request){
            return new ModelAndView("registro", model);
        }
	
	@RequestMapping(value="/iniciarSesion", method = RequestMethod.GET)
        public ModelAndView iniciarSesion(ModelMap model,HttpServletRequest request){
		String u = request.getParameter("correo");
		String c = request.getParameter("psswd");
		
		//incompleto
		
        	Usuario user = usuario_db.getUsuario(u, c);
            
        	String info = "";
         	if (user == null){
                	model.addAttribute("info", info+"Correo o contraseña inválidos.");
                        return new ModelAndView("iniciarSesion", model);
            	}else{
                	model.addAttribute("usuarioActivo", user.getUsuario_id());
            	}
            	return new ModelAndView("inicio",model);
        
        }
    @RequestMapping(value="/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(ModelMap model, HttpServletRequest request){
        String correo = request.getParameter("correo");
        String contra = request.getParameter("psswd");
        String nombre = request.getParameter("nombre");
        
        Usuario user = new Usuario(nombre, correo, contra);
        
        if(usuario_db.guardar(user)){
            model.addAttribute("usuarioActivo", usuario_db.getUsuario(correo, contra).getUsuario_id());
            return new ModelAndView("inicio", model);
        }else{
            model.addAttribute("info", "Registro fallido");

            return new ModelAndView("registro", model);
        }
        
        
        
    }
}
