-- nfplatform.artist definition

CREATE TABLE `artist` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `user_id` int(11) NOT NULL,
                          `name` varchar(256) NOT NULL,
                          `vote` int(11) NOT NULL DEFAULT 0,
                          `bio` text DEFAULT NULL,
                          `instagram` varchar(512) DEFAULT NULL,
                          `piece_count` int(11) NOT NULL DEFAULT 0,
                          `max_piece_price` int(11) NOT NULL DEFAULT 0,
                          `total_piece_price` int(11) NOT NULL DEFAULT 0,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- nfplatform.auction definition

CREATE TABLE `auction` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `piece_id` int(11) NOT NULL,
                           `seller_id` int(11) NOT NULL,
                           `klay` int(11) NOT NULL DEFAULT 0,
                           `nfpt` int(11) NOT NULL DEFAULT 0,
                           `title` varchar(256) NOT NULL,
                           `bio` text NOT NULL,
                           `sub_link` varchar(256) DEFAULT NULL,
                           `status` varchar(16) NOT NULL DEFAULT 'SELL',
                           `category_id` int(11) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- nfplatform.category definition

CREATE TABLE `category` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(256) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- nfplatform.ownership definition

CREATE TABLE `ownership` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `owner_id` int(11) NOT NULL,
                             `piece_id` int(11) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- nfplatform.piece definition

CREATE TABLE `piece` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `contract_hex` varchar(256) NOT NULL,
                         `artist_id` int(11) NOT NULL,
                         `vote` int(11) NOT NULL DEFAULT 0,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- nfplatform.`user` definition

CREATE TABLE `user` (
                        `id` int(11) NOT NULL,
                        `kakao_id` varchar(256) NOT NULL,
                        `token` varchar(256) NOT NULL,
                        `contract_account_hex` varchar(256) DEFAULT NULL,
                        `name` varchar(256) NOT NULL,
                        `klay` int(11) NOT NULL DEFAULT 0,
                        `set_img` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;