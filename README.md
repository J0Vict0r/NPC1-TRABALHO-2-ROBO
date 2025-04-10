# Robô Logo – Simulador de Movimentos com Orientação a Objetos (Java)

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos, com o objetivo de simular o comportamento de um robô em um plano cartesiano feito em Java, utilizando conceitos como exceções, herança, polimorfismo e classes abstratas.

## Objetivo

Controlar um robô (ou dois robôs, podendo também ser um inteligente, onde possui propriedades automáticas de desvio de obstáculos quando já colidiu uma vez, diferentemente do robô comum, que bate randomicamente até sair por conta própria) dentro de uma matriz 4x4, movimentando-o para encontrar um alimento posicionado pelo usuário, respeitando restrições de movimento e obstáculos no ambiente.

## Funcionalidades

- Representação de robôs com posição e cor.
- Movimentação por comandos de texto ou números (sobrecarga).
- O usuário define a posição do alimento na matriz (plano cartesiano em questão)
- Tratamento de exceções com `MovimentoInvalidoException`.
- Verificação de colisão com obstáculos:
  - **Bomba**: destrói o robô ou paralisa (como no caso deste código)
  - **Rocha**: impede a movimentação.
- Exibição da matriz com a movimentação dos robôs e obstáculos.
- Subclasse `RoboInteligente`, que evita repetir movimentos inválidos.
- Múltiplos modos de simulação (cada uma abaixo corresponde a uma classe Main respectivamente):
  - Controle manual do robô.
  - Dois robôs se movendo aleatoriamente.
  - Um robô comum vs um robô inteligente se movendo aleatoriamente sem obstáculos.
  - Robô comum vs robô inteligente se movendo aleatoriamente com obstáculos.

## Conteúdo abordado
-Programação Orientada a Objetos 

## Tecnologias

- Java
- Eclipse IDE

# Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/J0Vict0r/NPC1-TRABALHO-2-ROBO.git
