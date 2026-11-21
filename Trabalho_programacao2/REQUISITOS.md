# REQUISITOS.md - Sistema de Gerenciamento (Trabalho_programacao2)

## Visão geral
Sistema de gerenciamento desenvolvido em Java, executado por linha de comando (CLI), com organização em pacotes (`app`, `modelo`, `persistencia`, `excecao`, `util`), persistência em arquivos `.csv` e uso de recursividade.

## Requisitos Funcionais

- **RF01 - Gerenciar Clientes (CRUD):** Permitir cadastrar, listar, buscar, atualizar e remover clientes.
- **RF02 - Gerenciar Produtos (CRUD):** Permitir cadastrar, listar, buscar, atualizar e remover produtos.
- **RF03 - Gerenciar Lojas (CRUD):** Permitir cadastrar, listar, buscar, atualizar e remover lojas.
- **RF04 - Gerenciar Vendas (CRUD):** Permitir cadastrar (registrar), listar, buscar, atualizar e remover vendas, associando cliente, produto e loja (loja opcional).
- **RF05 - Menu principal e submenus:** Exibir menu principal com acesso aos módulos de Clientes, Produtos, Lojas e Vendas, além das opções de voltar/sair conforme o contexto.
- **RF06 - Operações por submenu:** Em cada submenu (Clientes, Produtos, Lojas e Vendas) disponibilizar as opções: Cadastrar, Listar, Buscar, Atualizar, Remover e Voltar.
- **RF07 - Persistência em arquivos:** Salvar e carregar os dados do sistema em arquivos de texto no formato `.csv` (um arquivo por entidade) ao iniciar o sistema e sempre que houver alterações (cadastro, atualização ou remoção).
- **RF08 - Controle de identificadores:** Gerar e controlar identificadores (IDs) das entidades para garantir unicidade dos registros.
- **RF09 - Busca de registros:** Permitir localizar registros por ID, informando mensagem adequada quando o registro não for encontrado.
- **RF10 - Validação de dados:** Validar entradas do usuário (por exemplo, campos obrigatórios, IDs e valores numéricos inválidos) e impedir operações com dados inválidos.
- **RF11 - Cálculo recursivo de total de vendas:** Calcular e exibir o valor total das vendas cadastradas utilizando uma função recursiva (soma recursiva baseada no preço dos produtos das vendas).

## Requisitos Não Funcionais

- **RNF01 - Linguagem e ambiente:** O sistema deve ser desenvolvido em Java e compatível com execução no VS Code (linha de comando/terminal).
- **RNF02 - Interface:** A interação com o usuário deve ser realizada por interface de linha de comando (CLI), com menus e submenus.
- **RNF03 - Arquitetura em pacotes:** O código deve ser organizado em pacotes, separando responsabilidades (aplicação, modelo, persistência, exceções e utilitários).
- **RNF04 - Tratamento de exceções:** O sistema deve tratar exceções com `try/catch`, incluindo `InputMismatchException`, `IOException` e exceções personalizadas para validação e para registros não encontrados.
- **RNF05 - Persistência:** Os dados devem ser persistidos em arquivos `.csv` (texto), com leitura ao iniciar e gravação a cada alteração.
- **RNF06 - Recursividade:** O sistema deve utilizar pelo menos duas funções recursivas em pontos aplicáveis (ex.: busca recursiva por ID em lista e soma recursiva do total de vendas; também pode haver repetição recursiva de leitura de opção inválida).
- **RNF07 - Legibilidade e documentação:** O código-fonte deve conter comentários em português, explicando classes, métodos e trechos relevantes.
- **RNF08 - Robustez:** O sistema não deve encerrar inesperadamente diante de entradas inválidas do usuário, apresentando mensagens de erro claras e permitindo nova tentativa.

## Funções recursivas implementadas (referência)
- **Busca recursiva por ID:** método recursivo para localizar um registro em uma lista percorrendo os índices recursivamente (utilizado nas operações de busca/apoio no sistema).
- **Soma recursiva do total de vendas:** método recursivo para somar o valor total das vendas cadastradas, percorrendo a lista de vendas recursivamente.
- **Leitura/validação recursiva de entrada de menu (apoio):** método recursivo para repetir a leitura quando o usuário informa uma opção inválida (tratando `InputMismatchException` e opções fora do intervalo), garantindo repetição sem encerrar o programa.
