#bin bash
boss_host_ip=192.168.200.129          #远程主机IP
boss_userid=liuyan #用户名
boss_password=liuyan #密码
boss_remote_dir=/opt/apache-tomcat-8.5.16/webapps/ROOT/WEB-INF/classes #远程主机路径
local_dir=/root/.jenkins/workspace/xicar_maven_origin_tomcat/src/main/resources #本机路径
begin_file=begin.txt
#校验当前是否存在相同文件，并按日期归档
echo "`date +%Y%m%d` `date +%H`:`date +%M`:`date +%S` ----Program  start!" >> $local_dir/getReport.log
if [ ! -f $local_dir/$begin_file ]
then
touch $local_dir/$begin_file
echo "`date +%Y%m%d`" > $local_dir/begin.txt
chmod 777 $local_dir/begin.txt
mdate=`awk '{print $1}' $local_dir/$begin_file`
echo "begin.txt=$mdate" >> $local_dir/getReport.log
#创建当前日志文件夹
if [ ! -d $local_dir/$mdate ]
then
mkdir $local_dir/$mdate
chmod 777 $local_dir/$mdate
echo "`date +%Y%m%d` `date +%H`:`date +%M`:`date +%S` --file creat over!" >> $local_dir/getReport.log
else
echo "File is exist!" >> $local_dir/getReport.log
fi
#连接远程服务器，并取文件到本地
ftp -n $boss_host_ip <<endl
user $boss_userid  $boss_password
prompt
cd $boss_remote_dir/
lcd $local_dir
mget *.xml *.properties
bye
endl
#校验文件数量是否正确
cd $local_dir
datcnt=`ls *.xml *.properties|wc | awk '{print $1}'`
rptcnt=16 #要求的文件数量
if [ $datcnt -ge $rptcnt ]
then
cat *$mdate*.rpt > $local_dir/check.txt
fi
fi
mv $local_dir/$begin_file $local_dir/over.txt
echo "`date +%Y%m%d` `date +%H`:`date +%M`:`date +%S` Program success!" >> $local_dir/getReport.log