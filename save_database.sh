# Backup storage directory 
backupfolder=/var/backups/position
# Notification email address 
recipient_email=bt@geo.sm
# MySQL user
user=root
# MySQL password
password=positionApi2021
#MySQL host
host=5.39.75.179
#MySQL port
port=9501
# Number of days to store the backup 
keep_day=30 
sqlfile=$backupfolder/position-$(date +%d-%m-%Y_%H-%M-%S).sql
zipfile=$backupfolder/position-$(date +%d-%m-%Y_%H-%M-%S).zip 
# Create a backup 
mysqldump --column-statistics=0 -h $host -P $port -u $user -p$password position > $sqlfile 
if [ $? == 0 ]; then
  echo 'Sql dump created' 
else
  echo 'mysqldump return non-zero code' | mailx -s 'No backup was created!' $recipient_email  
  exit 
fi 
# Compress backup 
zip $zipfile $sqlfile 
if [ $? == 0 ]; then
  echo 'The backup was successfully compressed' 
else
  echo 'Error compressing backup' | mailx -s 'Backup was not created!' $recipient_email 
  exit 
fi 
rm $sqlfile 
echo $zipfile | mailx -s 'Backup was successfully created' $recipient_email 
# Delete old backups 
find $backupfolder -mtime +$keep_day -delete