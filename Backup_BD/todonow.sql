-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30/04/2023 às 19:53
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `todonow`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `projects`
--

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `projects`
--

INSERT INTO `projects` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(1, 'ToDoNow (DEV)', 'Projeto de gestão de tarefas', '2023-04-30 00:00:00', '2023-04-30 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `idProject` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `completed` tinyint(1) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `deadline` date NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tasks`
--

INSERT INTO `tasks` (`id`, `idProject`, `name`, `description`, `completed`, `notes`, `deadline`, `created_at`, `updated_at`) VALUES
(1, 1, 'Criação de conceito de tags', 'Implementar o conceito de Tags para que se possa atribuir tags a Tarefa.', 0, '', '2023-11-20', '2023-04-30 00:00:00', '2023-04-30 00:00:00'),
(2, 1, 'Criação do conceito de conta e usuário', 'Implementação do conceito de Conta e Usuário. Para que mais usuários possam utilizar a aplicação.', 0, '', '2024-02-02', '2023-04-30 00:00:00', '2023-04-30 00:00:00'),
(3, 1, 'Tela de Login', 'Construção de uma tela de Login (feita após a criação dos conceitos de conta e usuário).', 0, '', '2023-12-05', '2023-04-30 00:00:00', '2023-04-30 00:00:00'),
(4, 1, 'Permitir a alteração dos dados da tarefa', 'Permitir que o usuário altere as informações complementares da Tarefa.', 0, '', '2023-10-28', '2023-04-30 00:00:00', '2023-04-30 00:00:00'),
(5, 1, 'Permitir que o usuário delete um projeto', 'Permitir que o usuário delete um projeto completo.', 0, '', '2023-12-28', '2023-04-30 00:00:00', '2023-04-30 00:00:00');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_projects` (`idProject`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `fk_projects` FOREIGN KEY (`idProject`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
