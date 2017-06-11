
## Dev provisioning

Using Vagrant and Ansible

```
$ vagrant up
$ vagrant ssh-config | sed -e 's/default/vagw/' >> ~/.ssh/config
$ ansible-playbook -i vagw, --tags instance --extra-vars "web_hostname=localhost" dev-instance.yml
$ curl http://localhost:8111/
$ ssh vagw
```

## Provisioning procedure

Using Ansible:

```
$ ansible-playbook -i git.work, --extra-vars "naked_hostname=git.work" prod-instance.yml
$ curl -i https://www.git.work/
$ ansible all -i git.work, -a 'systemctl status nginx.service'
```

# Deployment
Inside screen, with git-watch:

```
git-work $ git-watch -i -- ./ansible/prod-deploy.sh
```

### Speed up

`--tags deploy --skip-tags install`

## Deployment procedure

> Work-in-progress

1. Get current git hash
2. Fetch repository
3. Pull branch
4. Compare changes
    1. if code changed, we code-deploy:
        1. Build the project artifact
        2. Determine current instance (blue/green)
        3. Deploy project artifact to where inactive instance is
        4. Start the new instance
        5. Wait for it to come up
        6. Change nginx target to the new instance
        7. Reload nginx config
        8. Shut down the old instance gracefully (? do we want to shut down ? could we just keep them running)
    2. if content changed, we content-deploy:
        1. Determine current instance (blue/green)
        2. Determine the current instance directory
        3. rsync the content from the repo to the current instance directory
        4. Optionally, send a jmx signal to the current instance

# Choice of www vs naked domain (git.work)

I chose to use a naked domain. Why?

- It reads better for developers! Adding extra www is not aesthetic
   although it would be for many other sites. For some I use naked for some I don't.
- It doesn't intend to hyper-scale, so no need for CNAMEs etc.
- It's non-critical, so again no need for CNAMEs etc.

