-- Roles
INSERT INTO tipoperfilusuario (descripcion) SELECT 'Inversionista' FROM dual 
WHERE NOT EXISTS (SELECT * FROM tipoperfilusuario WHERE descripcion = 'Inversionista');

INSERT INTO tipoperfilusuario (descripcion) SELECT 'Empresa' FROM dual
WHERE NOT EXISTS (SELECT * FROM tipoperfilusuario WHERE descripcion = 'Empresa');

INSERT INTO tipoperfilusuario (descripcion) SELECT 'Admin' FROM dual
WHERE NOT EXISTS (SELECT * FROM tipoperfilusuario WHERE descripcion = 'Admin');

-- Monedas
INSERT INTO moneda (descripcion) SELECT 'PEN' FROM dual
WHERE NOT EXISTS (SELECT * FROM moneda WHERE descripcion = 'PEN');

INSERT INTO moneda (descripcion) SELECT 'USD' FROM dual
WHERE NOT EXISTS (SELECT * FROM moneda WHERE descripcion = 'USD');

-- Tipos de cuenta bancaria
INSERT INTO tipocuentabancaria (descripcion) SELECT 'Cuenta Ahorro' FROM dual
WHERE NOT EXISTS (SELECT * FROM tipocuentabancaria WHERE descripcion = 'Cuenta Ahorro');

INSERT INTO tipocuentabancaria (descripcion) SELECT 'Cuenta Corriente' FROM dual
WHERE NOT EXISTS (SELECT * FROM tipocuentabancaria WHERE descripcion = 'Cuenta Corriente');

-- Banco
INSERT INTO banco (descripcion) SELECT 'Banco de Crédito del Perú' FROM dual
WHERE NOT EXISTS (SELECT * FROM banco WHERE descripcion = 'Banco de Crédito del Perú');

-- Administrador
INSERT INTO cuentausuario (nombres, apellidomaterno, apellidopaterno, email, contraseña) 
SELECT 'Admin', 'Admin', 'Admin', 'Admin@gmail.com', '$2a$10$5fcdv48WuKjyyiBCeNnsZOnk1cneDHqrksqVksdkIuqK5133mPtFG' 
FROM dual WHERE NOT EXISTS (SELECT * FROM cuentausuario WHERE idcuentausuario = 1);

INSERT INTO cuenta_tipo_usuario (idcuentausuario, idtipoperfilusuario) 
SELECT 1,3 FROM dual WHERE NOT EXISTS (SELECT * FROM cuenta_tipo_usuario WHERE idcuentausuario = 1);

INSERT INTO cartera (cantidad, idcuentausuario, idmoneda) SELECT 0,1,1 UNION ALL SELECT 0,1,2 
WHERE NOT EXISTS (SELECT * FROM cartera WHERE idcuentausuario = 1);

INSERT INTO cuentabancariausuario (cci, numerocuenta, idbanco, idcuentausuario, idmoneda, idtipocuentabancaria)
SELECT '999999999999999', '999999999',1,1,1,1 FROM dual WHERE NOT EXISTS (SELECT * FROM cuentabancariausuario WHERE idcuentausuario = 1);
