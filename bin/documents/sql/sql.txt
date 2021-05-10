--テーブルがあった場合は事前に削除
DROP TABLE IF EXISTS administrators CASCADE;
DROP TABLE IF EXISTS employees CASCADE;

-- 管理者情報テーブル
create table administrators(
  id serial primary key,
  name text not null,
  mail_address text not null unique,
  password text not null
);

-- 従業員情報テーブル
create table employees (
  id integer primary key,
  name text not null,
  image text not null,
  gender text not null,
  hire_date timestamp not null,
  mail_address text not null unique,
  zip_code text not null,
  address text not null,
  telephone text not null,
  salary integer not null,
  characteristics text not null,
  dependents_count integer not null default 0
);

-- 管理者情報インサートデータ
insert into administrators(name,mail_address,password) values('伊賀将之','iga@sample.com','testtest');

-- 従業員情報インサートデータ
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(1,'山田太郎',  'e1.png','男性', '2012/11/29', 'taro.yamada@sample.com'		,'000-0000', '北海道札幌市1-1-1'		, '090-0000-0000', 400000, '山田太郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 3);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(2,'山田花子',  'e2.png','女性', '2013/01/03', 'hanako.yamada@sample.com'	,'111-1111', '青森県青森市1-1-1'		, '090-1111-1111', 300000, '山田花子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(3,'鈴木一朗',  'e1.png','男性', '2004/10/21', 'ichiro.suzuki@sample.com'	,'222-2222', '岩手県盛岡市1-1-1'		, '090-2222-2222', 200000, '鈴木一朗さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 4);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(4,'鈴木一子',  'e2.png','女性', '2003/04/05', 'ichiko.suzuki@sample.com'	,'333-3333', '宮城県仙台市1-1-1'		, '090-3333-3333', 100000, '鈴木一子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 2);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(5,'佐藤次郎',  'e1.png','男性', '2017/08/22', 'jiro.sato@sample.com'		,'444-4444', '秋田県秋田市1-1-1'		, '090-4444-4444', 450000, '佐藤次郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(6,'佐藤次子',  'e2.png','女性', '2014/03/04', 'jiko.sato@sample.com'		,'555-5555', '山形県山形市1-1-1'		, '090-5555-5555', 250000, '佐藤次子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(7,'渡辺三郎',  'e1.png','男性', '2018/10/29', 'saburo.watanabe@sample.com'	,'666-6666', '福島県福島市1-1-1'		, '090-6666-6666', 130000, '渡辺三郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(8,'渡辺三子',  'e2.png','女性', '2003/10/03', 'sanko.watanabe@sample.com'	,'777-7777', '茨城県水戸市1-1-1'		, '090-7777-7777', 320000, '渡辺三子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(9,'吉田四郎',  'e1.png','男性', '2012/11/29', 'shiro.yoshida@sample.com'	,'888-8888', '栃木県宇都宮市1-1-1'		, '090-8888-8888', 230000, '吉田四郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 2);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(10,'吉田四子', 'e2.png','女性', '2013/01/03', 'shiko.yoshida@sample.com'	,'999-9999', '群馬県前橋市1-1-1'		, '090-9999-9999', 455000, '吉田四子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 3);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(11,'高橋五郎', 'e1.png','男性', '2014/02/24', 'goro.takahashi@sample.com'	,'000-0000', '埼玉県さいたま市1-1-1'	, '080-0000-0000', 180000, '高橋五郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 2);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(12,'高橋五子', 'e2.png','女性', '2008/07/28', 'goko.takahashi@sample.com'	,'111-1111', '千葉県千葉市1-1-1'		, '080-1111-1111', 370000, '高橋五子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(13,'田中六郎', 'e1.png','男性', '2006/11/29', 'rokuro.tanaka@sample.com'	,'222-2222', '東京都新宿区1-1-1'		, '080-2222-2222', 210000, '田中六郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(14,'田中六子', 'e2.png','女性', '2009/01/03', 'rokuko.tanaka@sample.com'	,'333-3333', '神奈川県横浜市1-1-1'		, '080-3333-3333', 320000, '田中六子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(15,'伊藤七郎', 'e1.png','男性', '2011/02/23', 'nanaro.ito@sample.com'		,'444-4444', '新潟県新潟市1-1-1'		, '080-4444-4444', 185000, '伊藤七郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(16,'伊藤七子', 'e2.png','女性', '2012/05/03', 'nanako.ito@sample.com'		,'555-5555', '富山県富山市1-1-1'		, '080-5555-5555', 149000, '伊藤七子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 0);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(17,'山本八郎', 'e1.png','男性', '2017/06/18', 'hachiro.yamamoto@sample.com','666-6666', '石川県金沢市1-1-1'		, '080-6666-6666', 200000, '山本八郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(18,'山本八子', 'e2.png','女性', '2004/01/03', 'hachiko.yamamoto@sample.com','777-7777', '福井県福井市1-1-1'		, '080-7777-7777', 300000, '山本八子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 2);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(19,'小林九郎', 'e1.png','男性', '2006/05/21', 'kyuro.kobayashi@sample.com'	,'888-8888', '山梨県甲府市1-1-1'		, '080-8888-8888', 400000, '小林九郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 3);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(20,'小林九子', 'e2.png','女性', '2016/06/05', 'kyuko.kobayashi@sample.com'	,'999-9999', '長野県長野市1-1-1'		, '080-9999-9999', 250000, '小林九子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 5);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(21,'加藤十郎', 'e1.png','男性', '2008/07/12', 'juro.kato@sample.com'		,'000-0000', '岐阜県岐阜市1-1-1'		, '070-0000-0000', 190000, '加藤十郎さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 2);
insert into employees(id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count) values(22,'加藤十子', 'e2.png','女性', '2002/08/23', 'juko.kato@sample.com'		,'111-1111', '静岡県静岡市1-1-1'		, '070-1111-1111', 220000, '加藤十子さんは明るく素直な性格です。リーダーシップを発揮します。新卒社員研修の時はグループ開発の時にリーダーを買ってでました。積極性も人間性も抜群です。周りに対する不満も聞いたことがありません。', 1);
