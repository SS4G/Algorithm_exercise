SRC_CODE_DIR = "/d/work_space/Algorithm_practice_py/AlgorithmTraining"
DST_CODE_DIR = "/home/szh-920/workspace/alg_linux2/AlgorithmTraining"
cd $SRC_CODE_DIR
git add .
git commit -m "sync"
git push origin dev0_py

ssh szh-920@10.103.243.175 cd $DST_CODE_DIR
ssh szh-920@10.103.243.175 cd git pull origin dev0_py


