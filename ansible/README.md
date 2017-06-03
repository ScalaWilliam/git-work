
```
$ ansible-galaxy install ansiblebit.oracle-java
$ ansible-galaxy install geerlingguy.nginx
$ ansible-playbook -i hostname, playbook.yml
```

## Deployment procedure

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
