# HarvestOrbs

> Um plugin moderno de orbs flutuantes para PaperSpigot, que replanta automaticamente plantações quebradas e traz vida ao seu servidor!

---

## ✨ Visão Geral

O **HarvestOrbs** permite que você adicione "Orbs" mágicas flutuantes ao mundo. Quando uma plantação próxima é quebrada, a orb dispara partículas e a planta é replantada automaticamente no estágio máximo de crescimento.

O projeto é escrito em Java, com arquitetura limpa, princípios SOLID, tarefas otimizadas e integração total com o ecossistema PaperSpigot.

---

## 🧩 Funcionalidades

- **Orbs flutuantes customizadas** com cabeças personalizadas e nomes visíveis
- **Replantio automático** de plantações próximas à orb (trigo, cenoura, batata, beterraba, nether wart, melão, abóbora, etc.)
- **Partículas animadas** entre a orb e a plantação
- **Gerenciamento via comandos** (dar e remover orbs facilmente)
- **Persistência das orbs** após reinício do servidor (usando Yaml)
- **Estrutura modular, seguindo boas práticas e SOLID**
- **Fácil configuração e extensão**

---

## 🚀 Instalação

1. Baixe a última versão do plugin (`HarvestOrbs.jar`) na [página de releases](#) ou compile usando o código fonte.
2. Coloque o arquivo `.jar` em `plugins/` do seu servidor PaperMC.
3. Reinicie ou use `/reload` no servidor.

---

## ⚙️ Comandos

| Comando                | Permissão                      | Descrição                                 |
|------------------------|-------------------------------|-------------------------------------------|
| `/orb give <tipo>`     | `harvestorbs.command`         | Dá uma orb do tipo selecionado ao player  |
| `/orb remove`          | `harvestorbs.command`         | Remove a orb mais próxima do jogador      |

---

## 📦 Configuração

- As orbs são salvas em `plugins/HarvestOrbs/orbs.yml`
- Para alterar o range de detecção/replantio, edite o valor do raio em `OrbListener.java` (ou torne isso configurável, caso deseje)

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- PaperMC API
- Aikar Commands (ACF)
- Lombok
- Adventure API (para textos coloridos)
- YAML para persistência
- Estrutura modular, SOLID e Clean Code

---

## 🤝 Contribuindo

Pull requests são **muito bem-vindos**! Sinta-se à vontade para abrir issues ou sugerir melhorias.

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 📷 Imagens

![image](https://github.com/user-attachments/assets/c74d2480-4c9c-46fe-b0c2-22823ea826aa)

