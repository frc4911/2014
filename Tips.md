#GitHub Guideline
##Basic  process of versioning

###update your local folder
* git fetch(every branches)
* gil pull(just from the tracking remote branch) 

###upload
1. git add . or  
git add fileName  

2. git commit -m "msg"  

3. git push  

###branches
* git branch (to see the existing branches)
* git branch newBranch
* git checkout destinationBranch
* git merge destinationBranch

###removing files
* git rm file
* git rm -r folder
* git branch -D branchName
(you need to __commit__your deletion)

##Useful commands
* to pull a branch that you cannot see on your local git
 *git checkout --track origin/branchName

to better understand the process of git versioning clekc [here](http://stackoverflow.com/questions/6143285/git-add-vs-push-vs-commit)