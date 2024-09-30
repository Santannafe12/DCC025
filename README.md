Instruções de compilação e execução:
	Para compilar e executar um projeto Java usando o Maven para o build de produção (build para produção), o processo segue os mesmos princípios gerais de compilação e execução. A seguir estão as etapas para realizar o build.

Passo 1: Instalar o Maven
Instalar e configurar corretamente o Maven em seu computador.

Passo 2: Configuração do pom.xml
O arquivo pom.xml é crucial para configurar o build de produção. Você pode adicionar um perfil de produção ao pom.xml para habilitar otimizações, como evitar testes durante o build ou gerar um JAR otimizado.

Passo 3: Comandos para executar o projeto
Para compilar o código Java, navegue até o diretório raiz do projeto (onde o pom.xml está localizado) e execute: mvn compile

Esse comando compila o código-fonte localizado em src/main/java e coloca os arquivos .class resultantes na pasta target/classes.

Passo 4: Executando o .jar
Uma vez gerado o JAR, você pode executar o projeto em produção usando o comando: java -jar target/DCC025-1.0-SNAPSHOT.jar


Passo 5: Logar com admin
login: admin / senha: admin
