-- nfplatform.artist definition

CREATE TABLE `artist` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `user_id` int NOT NULL,
                          `name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
                          `vote` int NOT NULL DEFAULT '0',
                          `bio` text COLLATE utf8mb4_general_ci,
                          `instagram` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `piece_count` int NOT NULL DEFAULT '0',
                          `max_piece_price` int NOT NULL DEFAULT '0',
                          `total_piece_price` int NOT NULL DEFAULT '0',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- nfplatform.auction definition

CREATE TABLE `auction` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `piece_id` int NOT NULL,
                           `seller_id` int NOT NULL,
                           `klay` int NOT NULL DEFAULT '0',
                           `nfpt` int NOT NULL DEFAULT '0',
                           `title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
                           `bio` text COLLATE utf8mb4_general_ci NOT NULL,
                           `sub_link` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `status` varchar(16) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'SELL',
                           `category_id` int NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- nfplatform.category definition

CREATE TABLE `category` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- nfplatform.ownership definition

CREATE TABLE `ownership` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `owner_id` int NOT NULL,
                             `piece_id` int NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- nfplatform.piece definition

CREATE TABLE `piece` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `contract_hex` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
                         `artist_id` int NOT NULL,
                         `vote` int NOT NULL DEFAULT '0',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- nfplatform.`user` definition

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `kakao_id` int NOT NULL,
                        `token` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
                        `name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Unnamed',
                        `klay` int NOT NULL DEFAULT '0',
                        `contract_account_hex` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `set_img` tinyint(1) NOT NULL DEFAULT '0',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
