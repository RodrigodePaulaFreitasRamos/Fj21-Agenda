package br.com.caelum.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;
@WebServlet("/add")
public class AdicionaContatoServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override		
	protected void service(HttpServletRequest request, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = arg1.getWriter();
		//buscando os parametros no request
		String nome= request.getParameter("nome");
		String endereco= request.getParameter("endereco");
		String email= request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		
//FAZENDO CONVERSÃO DA DATA
		try{
			Date date =
					new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		}catch (ParseException e){
			out.println("Erro de Conversão da Data");
			return; //para a execução do metodo
		}
		

	//monta um objeto contato
	Contato contato = new Contato();
	contato.setNome(nome);
	contato.setEndereco(endereco);
	contato.setEmail(email);
	contato.setDataNascimento(dataNascimento);

	//Salva contato
	ContatoDao dao = new ContatoDao();
	dao.adiciona(contato);

	//imprime o nome do contato que foi adicionado
	out.println("<html>");
	out.println("<body>");
	out.println("<Contato>"+contato.getNome() +
			"adicionado com sucesso");
	out.println("</body>");
	out.println("</html>");	
	}

}
