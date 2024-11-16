-- alter table products add column url_imagem varchar2;

insert into customers(cpf, name, password, email, enable, customer_since)
values ('70486214435', 'João Paulo Lira de Almeida', '$2a$10$qTMLoDgpQO5kHSo9kTSl2ut7CIyTZiYyvd41cBuGv4LmHRiUrlrA.', 'jp.almeida@gmail.com', TRUE, current_date);

insert into products(name, describe, price, in_stock, url_imagem)
values ('Pão', '', 0.45, TRUE, 'https://cdn.2rscms.com.br/imgcache/5054/uploads/5054/layout/Linha%20Gold%20Paes/pao-frances-12h-gg.png.webp'),
       ('Ovo', ' O ovo começa a se formar a partir da gema, que é o (grande) óvulo da galinha e mede cerca de quatro centímetros de diâmetro. A partir daí, ele passa por várias partes do oviduto —uma espécie de canal que compõe o sistema genital da galinha e que se estende desde o ovário até a cloaca (por onde sai o ovo). Ao passar pelas várias estruturas desse canal, o ovo recebe diferentes nutrientes e sais minerais para proteger a gema, formar a clara e a casca do ovo. E é também ao passar pelo oviduto que essa "tinta", ou melhor, esses pigmentos, são adicionados à casca. Enquanto o processo de formação da casca e de sua coloração leva em média 21 horas, a formação dos componentes internos do ovo leva por volta de 4 horas.'
              , 1.0, TRUE,'https://giassi.vtexassets.com/arquivos/ids/2044967-800-auto?v=638589052301500000&width=800&height=auto&aspect=true'),
       ('Queijo', '', 27, TRUE, 'https://feiraplan.com.br/wp-content/uploads/2023/12/Queijo-Fresco.png'),
       ('Presunto', '', 32, TRUE, null),
       ('Leite', '', 27, TRUE, null);
       --('Leite', '', 27, TRUE, 'https://media.istockphoto.com/id/155749055/pt/foto/caixa-de-leite.jpg?s=612x612&w=0&k=20&c=aCdr76mGrBPAtc_kxO5vOUMfbcv_BNmQ808dGtgBl_8=');


