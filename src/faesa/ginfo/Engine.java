package faesa.ginfo;

import java.util.Random;
import java.util.Scanner;

public class Engine {

	String[] usuario = { "João", "José", "Maria", "Josias", "Arnaldo" };
	String[] jogo = { "Crossfire", "League of legends", "Resident Evil", "Pubg", "Call of duty" };
	String[] atributo = { "Horas jogadas", "Nota" };
	String[][][] registro = new String[usuario.length][jogo.length][atributo.length];

	Scanner scanner = new Scanner(System.in);

	public Engine() {
		for (int i = 0; i < usuario.length; i++) {
			for (int j = 0; j < jogo.length; j++) {
				for (int k = 0; k < atributo.length; k++) {
					registro[i][j][k] = new Random().nextInt(10) + "," + new Random().nextInt(10) + ""
							+ new Random().nextInt(10);
				}
			}
		}
	}

	public void start() {
		this.exibeBoasVindas();
		while(true) {
			this.exibeDados();
		}
	}

	private void exibeBoasVindas() {
		System.out.println();
		System.out.println("/**************************************************/");
		System.out.println("/           Bem vindo ao sistema GINFO!            /");
		System.out.println("/**************************************************/");
		System.out.println();
		this.loading("Carregando informações", 4);
	}

	private void loading(String mensagem, int tempo) {
		for (int i = 0; i < tempo; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 0) {
				System.out.print(mensagem);
			} else if (i == (tempo - 1)) {
				System.out.println(".");
			} else {
				System.out.print(".");
			}
		}
	}

	public void exibeDados() {
		System.out.println("/**************************************************/");
		System.out.println("/LISTA DE USUARIOS                                 /");
		System.out.println("/**************************************************/");
		for (int i = 0; i < usuario.length; i++) {
			System.out.println(i + " - " + usuario[i]);
		}
		System.out.println("/**************************************************/");
		System.out.println("/LISTA DE JOGOS                                    /");
		System.out.println("/**************************************************/");
		for (int i = 0; i < jogo.length; i++) {
			System.out.println(i + " - " + jogo[i]);
		}
		System.out.println("/**************************************************/");
		System.out.println("/LISTA DE REGISTROS                                /");
		System.out.println("/**************************************************/");
		for (int i = 0; i < usuario.length; i++) {
			System.out.println("//////////////////////////////////////////////////");
			System.out.println("Usuário: " + i + " - " + usuario[i]);
			System.out.println("//////////////////////////////////////////////////");
			for (int j = 0; j < jogo.length; j++) {
				System.out.println("--------------------------------------------------");
				System.out.println("     Jogo: " + j + " - " + jogo[j]);
				for (int k = 0; k < atributo.length; k++) {
					String registroAtual = registro[i][j][k];
					System.out.println("          " + atributo[k] + ": "
							+ (registroAtual == null ? "Não cadastrado" : registroAtual));
				}
				System.out.println("--------------------------------------------------");
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("* Digite S se desejar sair do sistema.");
		System.out.println();

		System.out.print("Digite o id do usuário que deseja alterar: ");
		String usuarioIdString = this.capturaDigitacao();
		if(usuarioIdString != null && usuarioIdString.toLowerCase().equals("s")) {
			this.sairDoSistema();
		}
		
		System.out.print("Digite o id do jogo que deseja alterar: ");
		String jogoIdString = this.capturaDigitacao();
		
		Integer usuarioId = this.stringToInt(usuarioIdString);
		Integer jogoId = this.stringToInt(jogoIdString);

		boolean existeUsuario = usuarioId != null && usuario[usuarioId] != null;
		boolean existeJogo = usuarioId != null && jogo[jogoId] != null;

		if (existeUsuario && existeJogo) {
			System.out.println();
			System.out.println("Usuário selecionado: " + usuario[usuarioId]);
			System.out.println("Jogo selecionado: " + jogo[jogoId]);
			System.out.println();
			System.out.println("* Aperte enter para manter o valor atual.");
			System.out.println();
			for (int i = 0; i < atributo.length; i++) {
				System.out.print("Digite o novo valor do atributo " + atributo[i] + " - ("
						+ registro[usuarioId][jogoId][i] + "): ");
				String atributo = this.capturaDigitacao();
				if(atributo != null && !atributo.isEmpty()) {					
					registro[usuarioId][jogoId][i] = atributo;
				}
			}
			System.out.println();
			System.out.println("Registro salvo com sucesso!");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Os dados digitados estão incorretos.");
			System.out.println();
		}
		
		this.loading("Atualizando dados", 4);
	}

	private String capturaDigitacao() {
		String resultado = null;
		try {
			resultado = scanner.nextLine();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Ocorreu um erro inesperado, informe ao administrador do sistema.");
			System.out.println();
			this.sairDoSistema();
		}
		return resultado;
	}

	private void sairDoSistema() {
		System.out.println();
		System.out.println();
		this.loading("Saindo do sistema", 5);
		System.out.println("Obrigado por usar o sistema GINFO, até logo!");
		System.exit(0);
	}

	private Integer stringToInt(String valor) {
		try {
			return Integer.parseInt(valor);
		} catch (Exception e) {
			return null;
		}
	}

}
