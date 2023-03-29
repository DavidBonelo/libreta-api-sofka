/******************** Add Table: contacto ************************/
/* Build Table Structure */
CREATE TABLE contacto
(
cnt_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
cnt_nombre VARCHAR(100) NOT NULL,
cnt_apellido VARCHAR(100) NOT NULL,
cnt_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
cnt_updated_at DATETIME NULL,
cnt_fecha_nacimiento DATETIME,
cnt_email VARCHAR(100),
cnt_deleted BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/* Add Indexes */
CREATE INDEX contacto_cnt_apellido_Idx ON contacto (cnt_apellido) USING BTREE;
CREATE INDEX contacto_cnt_nombre_Idx ON contacto (cnt_nombre) USING BTREE;
/******************** Add Table: telefono ************************/
/* Build Table Structure */
CREATE TABLE telefono
(
tel_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
tel_contacto_id INTEGER UNSIGNED NOT NULL,
tel_telefono VARCHAR(30) NOT NULL,
tel_created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
tel_updated_at DATETIME NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/* Add Indexes */
CREATE UNIQUE INDEX telefono_tel_contacto_id_tel_telefono_Idx ON telefono (tel_contacto_id, tel_telefono) USING BTREE;
CREATE INDEX telefono_tel_telefono_Idx ON telefono (tel_telefono) USING BTREE;
CREATE INDEX telefono_tel_usuario_id_Idx ON telefono (tel_contacto_id) USING BTREE;
/************ Add Foreign Keys ***************/
/* Add Foreign Key: fk_telefono_contacto */
ALTER TABLE telefono ADD CONSTRAINT fk_telefono_contacto
FOREIGN KEY (tel_contacto_id) REFERENCES contacto (cnt_id)
ON UPDATE CASCADE ON DELETE RESTRICT;
