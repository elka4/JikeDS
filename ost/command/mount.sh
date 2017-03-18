MOUNT(8)                   Linux Programmer’s Manual                  MOUNT(8)



NAME
       mount - mount a filesystem

SYNOPSIS
       mount [-lhV]

       mount -a [-fFnrsvw] [-t vfstype] [-O optlist]

       mount [-fnrsvw] [-o option[,option]...]  device|dir

       mount [-fnrsvw] [-t vfstype] [-o options] device dir

DESCRIPTION
       All files accessible in a Unix system are arranged in one big tree, the
       file hierarchy, rooted at /.  These files can be spread out  over  sev-
       eral  devices.  The mount command serves to attach the filesystem found
       on some device to the big file tree. Conversely, the umount(8)  command
       will detach it again.

       The standard form of the mount command, is

              mount -t type device dir

       This  tells  the kernel to attach the filesystem found on device (which
       is of type type) at the directory dir.  The previous contents (if  any)
       and  owner  and  mode  of  dir  become  invisible,  and as long as this
       filesystem remains mounted, the pathname dir refers to the root of  the
       filesystem on device.

       The listing and help.
              Three forms of invocation do not actually mount anything:

              mount -h
                     prints a help message

              mount -V
                     prints a version string

              mount [-l] [-t type]
                     lists all mounted filesystems (of type type).  The option
                     -l adds the labels in this listing.  See below.

       The device indication.
              Most devices are indicated by a file name (of  a  block  special
              device),  like /dev/sda1, but there are other possibilities. For
              example, in the case of an  NFS  mount,  device  may  look  like
              knuth.cwi.nl:/dir.   It  is possible to indicate a block special
              device using its volume LABEL or UUID (see the -L and -U options
              below).

              The  recommended  setup  is  to use LABEL=<label> or UUID=<uuid>
              tags rather than /dev/disk/by-{label,uuid} udev symlinks in  the
              /etc/fstab   file.  The  tags  are  more  readable,  robust  and
              portable. The mount(8) command internally uses udev symlinks, so
              use   the   symlinks   in   /etc/fstab  is  not  advantage  over
              LABEL=/UUID=.  For more details see libblkid(3).

              The proc filesystem is not associated with a special device, and
              when mounting it, an arbitrary keyword, such as proc can be used
              instead of a device specification.  (The customary  choice  none
              is less fortunate: the error message ‘none busy’ from umount can
              be confusing.)

       The /etc/fstab, /etc/mtab and /proc/mounts files.
              The file /etc/fstab (see fstab(5)), may contain lines describing
              what devices are usually mounted where, using which options.

              The command

                     mount -a [-t type] [-O optlist]

              (usually given in a bootscript) causes all filesystems mentioned
              in fstab (of the proper type and/or having  or  not  having  the
              proper  options)  to  be  mounted as indicated, except for those
              whose line contains the noauto keyword.  Adding  the  -F  option
              will make mount fork, so that the filesystems are mounted simul-
              taneously.

              When mounting a filesystem mentioned in fstab or mtab,  it  suf-
              fices to give only the device, or only the mount point.


              The  programs  mount  and  umount  maintain  a list of currently
              mounted filesystems in the file /etc/mtab.  If no arguments  are
              given to mount, this list is printed.

              The  mount  program  does not read the /etc/fstab file if device
              (or LABEL/UUID) and dir are specified. For example:

                     mount /dev/foo /dir

              If you want to override mount options from /etc/fstab  you  have
              to use:

                     mount device|dir -o <options>

              and then the mount options from command line will be appended to
              the list of options from /etc/fstab.   The  usual  behaviour  is
              that the last option wins if there is more duplicated options.

              When  the  proc  filesystem is mounted (say at /proc), the files
              /etc/mtab and /proc/mounts have very similar contents. The  for-
              mer  has  somewhat  more  information, such as the mount options
              used, but is not  necessarily  up-to-date  (cf.  the  -n  option
              below).  It  is possible to replace /etc/mtab by a symbolic link
              to /proc/mounts, and especially when you have very large numbers
              of mounts things will be much faster with that symlink, but some
              information is lost that way, and in particular using the "user"
              option will fail.

       The non-superuser mounts.
              Normally,  only  the  superuser can mount filesystems.  However,
              when fstab contains the user option on a line, anybody can mount
              the corresponding system.

              Thus, given a line

                     /dev/cdrom  /cd  iso9660  ro,user,noauto,unhide

              any  user  can  mount  the iso9660 filesystem found on his CDROM
              using the command

                     mount /dev/cdrom

              or

                     mount /cd

              For more details, see fstab(5).  Only the user  that  mounted  a
              filesystem  can unmount it again.  If any user should be able to
              unmount, then use users instead of user in the fstab line.   The
              owner option is similar to the user option, with the restriction
              that the user must be the owner of the special file. This may be
              useful e.g. for /dev/fd if a login script makes the console user
              owner of this device.  The group option  is  similar,  with  the
              restriction  that  the  user  must be member of the group of the
              special file.


       The bind mounts.
              Since Linux 2.4.0 it is possible to remount  part  of  the  file
              hierarchy somewhere else. The call is
mount.sh

       The bind mounts.
              Since Linux 2.4.0 it is possible to remount  part  of  the  file
              hierarchy somewhere else. The call is
                     mount --bind olddir newdir
              or shortoption
                     mount -B olddir newdir
              or fstab entry is:
                     /olddir /newdir none bind

              After  this  call the same contents is accessible in two places.
              One can also remount a single file (on a single file).

              This call attaches only (part of) a single filesystem, not  pos-
              sible  submounts.  The entire file hierarchy including submounts
              is attached a second place using
                     mount --rbind olddir newdir
              or shortoption
                     mount -R olddir newdir

              Note that the filesystem mount options will remain the  same  as
              those  on  the  original  mount  point, and cannot be changed by
              passing the -o  option  along  with  --bind/--rbind.  The  mount
              options  can be changed by a separate remount command, for exam-
              ple:

                     mount --bind olddir newdir
                     mount -o remount,ro newdir


       The move operation.
              Since Linux 2.5.1 it is possible to atomically  move  a  mounted
              tree to another place. The call is
                     mount --move olddir newdir
              or shortoption
                     mount -M olddir newdir
              This  will  cause  the  contents which previously appeared under
              olddir to be accessed under newdir.  The  physical  location  of
              the files is not changed.

              Note  also  that moving a mount residing under a shared mount is
              invalid and unsupported (in the other words the  parent  of  the
              olddir    has   to   use   private   propagation   flag).    See
              /proc/self/mountinfo for the current propagation flags.

       The shared subtrees operations.
              Since Linux 2.6.15 it is possible to mark a mount and  its  sub-
              mounts  as  shared, private, slave or unbindable. A shared mount
              provides ability to create  mirrors  of  that  mount  such  that
              mounts  and  umounts  within any of the mirrors propagate to the
              other mirror. A slave mount receives propagation from  its  mas-
              ter,  but any not vice-versa.  A private mount carries no propa-
              gation abilities.  A unbindable mount is a private  mount  which
              cannot  cloned  through  a bind operation. Detailed semantics is
              documented in Documentation/sharedsubtree.txt file in the kernel
              source tree.

                     mount --make-shared mountpoint
                     mount --make-slave mountpoint
                     mount --make-private mountpoint
                     mount --make-unbindable mountpoint

              The following commands allows one to recursively change the type
              of all the mounts under a given mountpoint.

                     mount --make-rshared mountpoint
                     mount --make-rslave mountpoint
                     mount --make-rprivate mountpoint
                     mount --make-runbindable mountpoint


COMMAND LINE OPTIONS
       The full set of mount options used by an invocation of mount is  deter-
       mined by first extracting the mount options for the filesystem from the
       fstab table, then applying any options specified by  the  -o  argument,
       and finally applying a -r or -w option, when present.

       Command line options available for the mount command:

       -V, --version
              Output version.

       -h, --help
              Print a help message.

       -v, --verbose
              Verbose mode.

       -a, --all
              Mount all filesystems (of the given types) mentioned in fstab.

       -F, --fork
              (Used  in  conjunction  with -a.)  Fork off a new incarnation of
              mount for each device.  This will do  the  mounts  on  different
              devices  or  different  NFS  servers  in parallel.  This has the
              advantage that it is faster; also NFS timeouts go in parallel. A
              disadvantage  is  that  the  mounts are done in undefined order.
              Thus, you cannot use this option if you want to mount both  /usr
              and /usr/spool.

       -f, --fake
              Causes  everything to be done except for the actual system call;
              if it’s not obvious, this  ‘‘fakes’’  mounting  the  filesystem.
              This  option is useful in conjunction with the -v flag to deter-
              mine what the mount command is trying to do. It can also be used
              to add entries for devices that were mounted earlier with the -n
              option. The -f option checks for existing  record  in  /etc/mtab
              and  fails when the record already exists (with regular non-fake
              mount, this check is done by kernel).

       -i, --internal-only
              Don’t  call  the  /sbin/mount.<filesystem>  helper  even  if  it
              exists.

       -l     Add  the  labels in the mount output. Mount must have permission
              to read the disk device (e.g. be suid root) for  this  to  work.
              One  can  set  such  a  label  for  ext2, ext3 or ext4 using the
              e2label(8) utility, or for XFS using xfs_admin(8), or for  reis-
              erfs using reiserfstune(8).

       -n, --no-mtab
              Mount without writing in /etc/mtab.  This is necessary for exam-
              ple when /etc is on a read-only filesystem.

       --no-canonicalize
              Don’t canonicalize paths. The mount  command  canonicalizes  all
              paths  (from  command  line  or  fstab) and stores canonicalized
              paths to the /etc/mtab file. This option can  be  used  together
              with the -f flag for already canonicalized absolut paths.

       -p, --pass-fd num
              In  case  of  a  loop mount with encryption, read the passphrase
              from file descriptor num instead of from the terminal.

       -s     Tolerate sloppy mount options rather  than  failing.  This  will
              ignore mount options not supported by a filesystem type. Not all
              filesystems support this option. This option exists for  support
              of the Linux autofs-based automounter.

       -r, --read-only
              Mount the filesystem read-only. A synonym is -o ro.

              Note  that,  depending  on the filesystem type, state and kernel
              behavior, the system may still write to the device. For example,
              Ext3 or ext4 will replay its journal if the filesystem is dirty.
              To prevent this kind of write access, you may want to mount ext3
              or  ext4  filesystem  with  "ro,noload" mount options or set the
:

              or  ext4  filesystem  with  "ro,noload" mount options or set the
              block device to read-only mode, see command blockdev(8).

       -w, --rw
              Mount the filesystem read/write. This is the default. A  synonym
              is -o rw.

       -L label
              Mount the partition that has the specified label.

       -U uuid
              Mount  the  partition  that  has  the specified uuid.  These two
              options require the file /proc/partitions (present  since  Linux
              2.1.116) to exist.

       -t, --types vfstype
              The argument following the -t is used to indicate the filesystem
              type.   The  filesystem  types  which  are  currently  supported
              include:  adfs,  affs,  autofs,  cifs,  coda,  coherent, cramfs,
              debugfs, devpts, efs, ext, ext2, ext3, ext4, hfs, hfsplus, hpfs,
              iso9660,  jfs, minix, msdos, ncpfs, nfs, nfs4, ntfs, proc, qnx4,
              ramfs, reiserfs, romfs, squashfs,  smbfs,  sysv,  tmpfs,  ubifs,
              udf,  ufs,  umsdos,  usbfs,  vfat, xenix, xfs, xiafs.  Note that
              coherent, sysv and xenix  are  equivalent  and  that  xenix  and
              coherent  will be removed at some point in the future — use sysv
              instead. Since kernel version 2.1.21 the types ext and xiafs  do
              not  exist anymore. Earlier, usbfs was known as usbdevfs.  Note,
              the real list of all supported filesystems depends on your  ker-
              nel.

              The  programs mount and umount support filesystem subtypes.  The
              subtype  is  defined  by   ’.subtype’   suffix.    For   example
              ’fuse.sshfs’.  It’s  recommended  to use subtype notation rather
              than  add  any  prefix  to  the  mount   source   (for   example
              ’sshfs#example.com’ is depreacated).

              For most types all the mount program has to do is issue a simple
              mount(2) system call, and no detailed knowledge of the  filesys-
              tem  type is required.  For a few types however (like nfs, nfs4,
              cifs, smbfs, ncpfs) ad hoc code is  necessary.  The  nfs,  nfs4,
              cifs,  smbfs,  and  ncpfs filesystems have a separate mount pro-
              gram. In order to make it possible to treat all types in a  uni-
              form  way,  mount  will execute the program /sbin/mount.TYPE (if
              that exists) when called with type TYPE.  Since various versions
              of  the  smbmount  program  have  different calling conventions,
              /sbin/mount.smbfs may have to be a shell script that sets up the
              desired call.

              If  no  -t  option  is  given, or if the auto type is specified,
              mount will try to guess the desired type.  Mount uses the  blkid
              or  volume_id  library for guessing the filesystem type; if that
              does not turn up anything that looks familiar, mount will try to
              read  the  file  /etc/filesystems,  or,  if that does not exist,
              /proc/filesystems.  All of the  filesystem  types  listed  there
              will  be tried, except for those that are labeled "nodev" (e.g.,
              devpts, proc and nfs).  If /etc/filesystems ends in a line  with
              a single * only, mount will read /proc/filesystems afterwards.

              The auto type may be useful for user-mounted floppies.  Creating
              a file /etc/filesystems can be useful to change the probe  order
              (e.g.,  to  try vfat before msdos or ext3 before ext2) or if you
              use a kernel module autoloader.  Warning:  the  probing  uses  a
              heuristic  (the presence of appropriate ‘magic’), and could rec-
              ognize the wrong filesystem  type,  possibly  with  catastrophic
              consequences.  If  your  data  is  valuable,  don’t ask mount to
              guess.

              More than one type may be specified in a comma  separated  list.
              The  list of filesystem types can be prefixed with no to specify
              the filesystem types on which no action should be taken.   (This
              can be meaningful with the -a option.) For example, the command:

                     mount -a -t nomsdos,ext

              mounts all filesystems except those of type msdos and ext.

       -O, --test-opts opts
              Used in conjunction with -a, to limit the set of filesystems  to
              which  the -a is applied.  Like -t in this regard except that it
              is useless except in the context of -a.  For example,  the  com-
              mand:

                     mount -a -O no_netdev

              mounts  all filesystems except those which have the option _net-
              dev specified in the options field in the /etc/fstab file.

              It is different from -t in that each option is matched  exactly;
              a  leading no at the beginning of one option does not negate the
              rest.

              The -t and -O options are cumulative in  effect;  that  is,  the
              command

                     mount -a -t ext2 -O _netdev

              mounts  all  ext2  filesystems  with the _netdev option, not all
              filesystems that are either ext2  or  have  the  _netdev  option
              specified.

       -o, --options opts
              Options  are  specified with a -o flag followed by a comma sepa-
              rated string of options. For example:

                     mount LABEL=mydisk -o noatime,nouser


              For more details, see FILESYSTEM INDEPENDENT MOUNT  OPTIONS  and
              FILESYSTEM SPECIFIC MOUNT OPTIONS sections.

       -B, --bind
              Remount  a  subtree  somewhere  else  (so  that its contents are
              available in both places). See above.

       -R, --rbind
              Remount a subtree and all possible submounts somewhere else  (so
              that its contents are available in both places). See above.

       -M, --move
              Move a subtree to some other place. See above.


FILESYSTEM INDEPENDENT MOUNT OPTIONS
       Some  of  these  options  are  only  useful  when  they  appear  in the
       /etc/fstab file.

       Some of these options could be enabled or disabled by  default  in  the
       system  kernel.  To  check  the  current  setting  see  the  options in
       /proc/mounts. Note that filesystems also have  per-filesystem  specific
       default  mount  options  (see  for  example  tune2fs -l output for extN
       filesystems).

       The following options apply to any filesystem  that  is  being  mounted
       (but  not every filesystem actually honors them - e.g., the sync option
       today has effect only for ext2, ext3, fat, vfat and ufs):


       async  All I/O to the filesystem should be  done  asynchronously.  (See
              also the sync option.)

       atime  Do  not  use noatime feature, then the inode access time is con-
              trolled by kernel defaults. See also the description for  stric-
              tatime and relatime mount options.

       noatime
              Do  not  update  inode access times on this filesystem (e.g, for
:

       noatime
              Do  not  update  inode access times on this filesystem (e.g, for
              faster access on the news spool to speed up news servers).

       auto   Can be mounted with the -a option.

       noauto Can only be mounted explicitly (i.e., the  -a  option  will  not
              cause the filesystem to be mounted).

       context=context,  fscontext=context,  defcontext=context  and  rootcon-
       text=context
              The  context= option is useful when mounting filesystems that do
              not support extended attributes, such as a floppy or  hard  disk
              formatted  with  VFAT,  or systems that are not normally running
              under SELinux, such as an ext3 formatted disk from a non-SELinux
              workstation. You can also use context= on filesystems you do not
              trust, such as a floppy. It also  helps  in  compatibility  with
              xattr-supporting filesystems on earlier 2.4.<x> kernel versions.
              Even where xattrs are supported, you can save time not having to
              label  every file by assigning the entire disk one security con-
              text.

              A commonly used  option  for  removable  media  is  context=sys-
              tem_u:object_r:removable_t.

              Two  other options are fscontext= and defcontext=, both of which
              are mutually exclusive of the context option. This means you can
              use fscontext and defcontext with each other, but neither can be
              used with context.

              The fscontext= option works for all filesystems,  regardless  of
              their  xattr  support. The fscontext option sets the overarching
              filesystem label to a specific security context. This filesystem
              label  is  separate  from the individual labels on the files. It
              represents the entire filesystem for certain kinds of permission
              checks,  such as during mount or file creation.  Individual file
              labels are still obtained from the xattrs  on  the  files  them-
              selves.  The  context option actually sets the aggregate context
              that fscontext provides, in addition to supplying the same label
              for individual files.

              You  can  set  the  default security context for unlabeled files
              using defcontext= option. This overrides the value set for unla-
              beled  files  in  the policy and requires a filesystem that sup-
              ports xattr labeling.

              The rootcontext= option allows you to explicitly label the  root
              inode of a FS being mounted before that FS or inode because vis-
              able to userspace. This was found to be useful for  things  like
              stateless linux.

              For more details, see selinux(8)


       defaults
              Use  default  options: rw, suid, dev, exec, auto, nouser, async,
              and relatime.

              Note that the real set of the all default mount options  depends
              on kernel and filesystem type. See the begin of this section for
              more details.

       dev    Interpret character or block special devices on the  filesystem.

       nodev  Do  not interpret character or block special devices on the file
              system.

       diratime
              Update directory inode access times on this filesystem. This  is
              the default.

       nodiratime
              Do not update directory inode access times on this filesystem.

       dirsync
              All  directory updates within the filesystem should be done syn-
              chronously.  This affects the  following  system  calls:  creat,
              link, unlink, symlink, mkdir, rmdir, mknod and rename.

       exec   Permit execution of binaries.

       noexec Do  not  allow  direct  execution of any binaries on the mounted
              filesystem.  (Until recently it was  possible  to  run  binaries
              anyway  using a command like /lib/ld*.so /mnt/binary. This trick
              fails since Linux 2.4.25 / 2.6.0.)

       group  Allow an ordinary (i.e., non-root) user to mount the  filesystem
              if  one  of  his  groups  matches the group of the device.  This
              option implies the options nosuid and nodev  (unless  overridden
              by subsequent options, as in the option line group,dev,suid).

       iversion
              Every  time  the  inode is modified, the i_version field will be
              incremented.

       noiversion
              Do not increment the i_version inode field.

       mand   Allow mandatory locks on this filesystem. See fcntl(2).

       nomand Do not allow mandatory locks on this filesystem.

       _netdev
              The filesystem resides on a device that requires network  access
              (used  to  prevent  the  system  from  attempting to mount these
              filesystems until the network has been enabled on the system).

       nofail Do not report errors for this device if it does not exist.

       relatime
              Update inode access times relative to  modify  or  change  time.
              Access time is only updated if the previous access time was ear-
              lier than the current modify or change time. (Similar  to  noat-
              ime,  but  doesn’t break mutt or other applications that need to
              know if a file has been read since the last time  it  was  modi-
              fied.)

              Since Linux 2.6.30, the kernel defaults to the behavior provided
              by this option (unless noatime was  specified), and the stricta-
              time  option  is  required  to  obtain traditional semantics. In
              addition, since Linux 2.6.30, the file’s  last  access  time  is
              always  updated  if  it  is more than 1 day old.

       norelatime
              Do  not  use  relatime  feature.  See also the strictatime mount
              option.

       strictatime
              Allows to explicitly requesting full atime updates.  This  makes
              it  possible  for  kernel to defaults to relatime or noatime but
              still allow userspace to override it. For more details about the
              default system mount options see /proc/mounts.

       nostrictatime
              Use  the  kernel’s  default  behaviour  for  inode  access  time
              updates.

       suid   Allow set-user-identifier or set-group-identifier bits  to  take
              effect.

       nosuid Do not allow set-user-identifier or set-group-identifier bits to
              take effect. (This seems safe, but is in fact rather  unsafe  if
              you have suidperl(1) installed.)

       owner  Allow  an ordinary (i.e., non-root) user to mount the filesystem
              if he is the owner of  the  device.   This  option  implies  the
:

              you have suidperl(1) installed.)

       owner  Allow  an ordinary (i.e., non-root) user to mount the filesystem
              if he is the owner of  the  device.   This  option  implies  the
              options  nosuid  and  nodev  (unless  overridden  by  subsequent
              options, as in the option line owner,dev,suid).

       remount
              Attempt to remount an already-mounted filesystem.  This is  com-
              monly  used  to  change  the mount flags for a filesystem, espe-
              cially to make a readonly  filesystem  writeable.  It  does  not
              change device or mount point.

              The remount functionality follows the standard way how the mount
              command works with options from fstab. It means the  mount  com-
              mand doesn’t read fstab (or mtab) only when a device and dir are
              fully specified.

              mount -o remount,rw /dev/foo /dir

              After this call all old mount options are replaced and arbitrary
              stuff  from  fstab  is ignored, except the loop= option which is
              internally generated and maintained by the mount command.

              mount -o remount,rw  /dir

              After this call mount reads fstab (or  mtab)  and  merges  these
              options with options from command line ( -o ).

       ro     Mount the filesystem read-only.

       _rnetdev
              Like  _netdev,  except  "fsck  -a" checks this filesystem during
              rc.sysinit.

       rw     Mount the filesystem read-write.

       sync   All I/O to the filesystem should be done synchronously. In  case
              of  media  with  limited number of write cycles (e.g. some flash
              drives) "sync" may cause life-cycle shortening.

       user   Allow an ordinary user to mount the filesystem.  The name of the
              mounting  user  is  written  to  mtab so that he can unmount the
              filesystem again.   This  option  implies  the  options  noexec,
              nosuid,  and  nodev (unless overridden by subsequent options, as
              in the option line user,exec,dev,suid).

       nouser Forbid an ordinary (i.e., non-root) user to mount  the  filesys-
              tem.  This is the default.

       users  Allow  every  user  to  mount  and unmount the filesystem.  This
              option implies the options noexec,  nosuid,  and  nodev  (unless
              overridden   by  subsequent  options,  as  in  the  option  line
              users,exec,dev,suid).


FILESYSTEM SPECIFIC MOUNT OPTIONS
       The following options apply only to certain filesystems.  We sort  them
       by filesystem. They all follow the -o flag.

       What  options  are supported depends a bit on the running kernel.  More
       info  may  be  found  in  the  kernel  source  subdirectory  Documenta-
       tion/filesystems.


Mount options for adfs
       uid=value and gid=value
              Set the owner and group of the files in the filesystem (default:
              uid=gid=0).

       ownmask=value and othmask=value
              Set the permission mask for ADFS ’owner’ permissions and ’other’
              permissions,  respectively  (default:  0700  and  0077,  respec-
              tively).    See    also    /usr/src/linux/Documentation/filesys-
              tems/adfs.txt.

Mount options for affs
       uid=value and gid=value
              Set  the owner and group of the root of the filesystem (default:
              uid=gid=0, but with option uid or gid without  specified  value,
              the uid and gid of the current process are taken).

       setuid=value and setgid=value
              Set the owner and group of all files.

       mode=value
              Set the mode of all files to value & 0777 disregarding the orig-
              inal permissions.  Add search  permission  to  directories  that
              have read permission.  The value is given in octal.

       protect
              Do  not allow any changes to the protection bits on the filesys-
              tem.

       usemp  Set uid and gid of the root of the filesystem to the uid and gid
              of the mount point upon the first sync or umount, and then clear
              this option. Strange...

       verbose
              Print an informational message for each successful mount.

       prefix=string
              Prefix used before volume name, when following a link.

       volume=string
              Prefix (of length at most 30) used before ’/’ when  following  a
              symbolic link.

       reserved=value
              (Default:  2.)  Number  of  unused  blocks  at  the start of the
              device.

       root=value
              Give explicitly the location of the root block.

       bs=value
              Give blocksize. Allowed values are 512, 1024, 2048, 4096.

       grpquota|noquota|quota|usrquota
              These options are accepted but ignored.  (However, quota  utili-
              ties may react to such strings in /etc/fstab.)


Mount options for cifs
       See the options section of the mount.cifs(8) man page (cifs-utils pack-
       age must be installed).


Mount options for coherent
       None.


Mount options for debugfs
       The debugfs filesystem is a pseudo filesystem, traditionally mounted on
       /sys/kernel/debug.  There are no mount options.


Mount options for devpts
       The  devpts filesystem is a pseudo filesystem, traditionally mounted on
       /dev/pts.  In order to acquire  a  pseudo  terminal,  a  process  opens
       /dev/ptmx;  the number of the pseudo terminal is then made available to
       the  process  and  the  pseudo  terminal  slave  can  be  accessed   as
       /dev/pts/<number>.

       uid=value and gid=value
              This  sets  the  owner or the group of newly created PTYs to the
:

       the  process  and  the  pseudo  terminal  slave  can  be  accessed   as
       /dev/pts/<number>.

       uid=value and gid=value
              This  sets  the  owner or the group of newly created PTYs to the
              specified values. When nothing is specified, they will be set to
              the  UID and GID of the creating process.  For example, if there
              is a tty group with GID 5, then gid=5 will cause  newly  created
              PTYs to belong to the tty group.

       mode=value
              Set  the mode of newly created PTYs to the specified value.  The
              default is 0600.  A value of mode=620 and gid=5 makes  "mesg  y"
              the default on newly created PTYs.

       newinstance
              Create  a  private  instance  of  devpts  filesystem,  such that
              indices of ptys allocated in this new instance  are  independent
              of indices created in other instances of devpts.

              All  mounts  of devpts without this newinstance option share the
              same set of pty indices (i.e legacy mode).  Each mount of devpts
              with the newinstance option has a private set of pty indices.

              This  option  is  mainly used to support containers in the linux
              kernel. It is implemented in linux kernel versions starting with
              2.6.29.   Further,  this  mount  option  is  valid  only if CON-
              FIG_DEVPTS_MULTIPLE_INSTANCES is enabled in the kernel  configu-
              ration.

              To  use  this  option  effectively, /dev/ptmx must be a symbolic
              link to pts/ptmx.  See  Documentation/filesystems/devpts.txt  in
              the linux kernel source tree for details.

       ptmxmode=value

              Set the mode for the new ptmx device node in the devpts filesys-
              tem.

              With the support for multiple instances of  devpts  (see  newin-
              stance  option  above), each instance has a private ptmx node in
              the root of the devpts filesystem (typically /dev/pts/ptmx).

              For compatibility with older versions of the kernel, the default
              mode  of  the new ptmx node is 0000.  ptmxmode=value specifies a
              more useful mode for the ptmx node  and  is  highly  recommended
              when the newinstance option is specified.

              This  option is only implemented in linux kernel versions start-
              ing with 2.6.29. Further this  option  is  valid  only  if  CON-
              FIG_DEVPTS_MULTIPLE_INSTANCES  is enabled in the kernel configu-
              ration.


Mount options for ext
       None.  Note that the ‘ext’ filesystem is obsolete. Don’t use it.  Since
       Linux version 2.1.21 extfs is no longer part of the kernel source.


Mount options for ext2
       The  ‘ext2’  filesystem  is the standard Linux filesystem.  Since Linux
       2.5.46, for most  mount  options  the  default  is  determined  by  the
       filesystem superblock. Set them with tune2fs(8).

       acl|noacl
              Support POSIX Access Control Lists (or not).

       bsddf|minixdf
              Set  the  behaviour  for  the  statfs  system  call. The minixdf
              behaviour is to return in the f_blocks field the total number of
              blocks  of  the  filesystem, while the bsddf behaviour (which is
              the default) is to subtract the overhead blocks used by the ext2
              filesystem and not available for file storage. Thus

              % mount /k -o minixdf; df /k; umount /k
              Filesystem   1024-blocks  Used Available Capacity Mounted on
              /dev/sda6      2630655   86954  2412169      3%   /k
              % mount /k -o bsddf; df /k; umount /k
              Filesystem   1024-blocks  Used Available Capacity Mounted on
              /dev/sda6      2543714      13  2412169      0%   /k

              (Note  that  this  example  shows  that one can add command line
              options to the options given in /etc/fstab.)


       check={none|nocheck}
              No checking is done at mount time. This is the default. This  is
              fast.   It  is wise to invoke e2fsck(8) every now and then, e.g.
              at boot time.

       debug  Print debugging info upon each (re)mount.

       errors={continue|remount-ro|panic}
              Define the behaviour when  an  error  is  encountered.   (Either
              ignore  errors  and  just mark the filesystem erroneous and con-
              tinue, or remount the filesystem read-only, or  panic  and  halt
              the  system.)   The default is set in the filesystem superblock,
              and can be changed using tune2fs(8).

       grpid|bsdgroups and nogrpid|sysvgroups
              These options define what group id a newly  created  file  gets.
              When  grpid  is  set,  it takes the group id of the directory in
              which it is created; otherwise (the default) it takes the  fsgid
              of  the current process, unless the directory has the setgid bit
              set, in which case it takes the gid from the  parent  directory,
              and also gets the setgid bit set if it is a directory itself.

       grpquota|noquota|quota|usrquota
              These options are accepted but ignored.

       nobh   Do not attach buffer_heads to file pagecache. (Since 2.5.49.)

       nouid32
              Disables  32-bit  UIDs  and  GIDs.  This is for interoperability
              with older kernels which only store and expect 16-bit values.

       oldalloc or orlov
              Use old allocator or Orlov allocator for new  inodes.  Orlov  is
              default.

       resgid=n and resuid=n
              The  ext2 filesystem reserves a certain percentage of the avail-
              able space (by default 5%, see mke2fs(8) and tune2fs(8)).  These
              options  determine  who  can use the reserved blocks.  (Roughly:
              whoever has the specified  uid,  or  belongs  to  the  specified
              group.)

       sb=n   Instead  of  block  1,  use block n as superblock. This could be
              useful when the filesystem has been damaged.   (Earlier,  copies
              of  the  superblock would be made every 8192 blocks: in block 1,
              8193, 16385, ... (and one got  thousands  of  copies  on  a  big
              filesystem).  Since  version  1.08,  mke2fs  has  a  -s  (sparse
              superblock) option to reduce the number of  backup  superblocks,
              and  since  version 1.15 this is the default. Note that this may
              mean that ext2 filesystems created by a recent mke2fs cannot  be
              mounted  r/w  under Linux 2.0.*.)  The block number here uses 1k
              units. Thus, if you  want  to  use  logical  block  32768  on  a
              filesystem with 4k blocks, use "sb=131072".

       user_xattr|nouser_xattr
              Support "user." extended attributes (or not).



Mount options for ext3
       The  ext3 filesystem is a version of the ext2 filesystem which has been
:

Mount options for ext3
       The  ext3 filesystem is a version of the ext2 filesystem which has been
       enhanced with journalling.  It supports the same  options  as  ext2  as
       well as the following additions:

       journal=update
              Update the ext3 filesystem’s journal to the current format.

       journal=inum
              When  a  journal  already exists, this option is ignored. Other-
              wise, it specifies the number of the inode which will  represent
              the  ext3  filesystem’s  journal  file;   ext3 will create a new
              journal, overwriting the old contents of the  file  whose  inode
              number is inum.

       journal_dev=devnum
              When  the  external  journal  device’s  major/minor numbers have
              changed, this option allows the user to specify the new  journal
              location.   The  journal  device  is  identified through its new
              major/minor numbers encoded in devnum.

       norecovery/noload
              Don’t load the journal on mounting.  Note that if the filesystem
              was not unmounted cleanly, skipping the journal replay will lead
              to the filesystem containing inconsistencies that  can  lead  to
              any number of problems.

       data={journal|ordered|writeback}
              Specifies  the  journalling  mode  for  file  data.  Metadata is
              always journaled.  To use modes other than ordered on  the  root
              filesystem,  pass the mode to the kernel as boot parameter, e.g.
              rootflags=data=journal.

              journal
                     All data is committed into the  journal  prior  to  being
                     written into the main filesystem.

              ordered
                     This  is  the  default mode.  All data is forced directly
                     out to the main file system prior to its  metadata  being
                     committed to the journal.

              writeback
                     Data ordering is not preserved - data may be written into
                     the main filesystem after its metadata has been committed
                     to  the  journal.   This  is  rumoured to be the highest-
                     throughput option.   It  guarantees  internal  filesystem
                     integrity,  however  it  can  allow old data to appear in
                     files after a crash and journal recovery.

       barrier=0 / barrier=1
              This enables/disables barriers.   barrier=0  disables  it,  bar-
              rier=1 enables it.  Write barriers enforce proper on-disk order-
              ing of journal commits, making volatile disk write  caches  safe
              to  use,  at  some  performance  penalty.   The  ext3 filesystem
              enables write barriers by default.  Be sure to  enable  barriers
              unless your disks are battery-backed one way or another.  Other-
              wise you risk filesystem corruption in case of power failure.

       commit=nrsec
              Sync all data and metadata  every  nrsec  seconds.  The  default
              value is 5 seconds. Zero means default.

       user_xattr
              Enable Extended User Attributes. See the attr(5) manual page.

       acl    Enable POSIX Access Control Lists. See the acl(5) manual page.


Mount options for ext4
       The  ext4  filesystem  is  an  an advanced level of the ext3 filesystem
       which incorporates scalability and reliability  enhancements  for  sup-
       porting large filesystem.

       The   options  journal_dev,  noload,  data,  commit,  orlov,  oldalloc,
       [no]user_xattr [no]acl, bsddf, minixdf, debug, errors, data_err, grpid,
       bsdgroups,  nogrpid  sysvgroups,  resgid,  resuid,  sb, quota, noquota,
       grpquota, usrquota and [no]bh are backwardly compatible  with  ext3  or
       ext2.

       journal_checksum
              Enable  checksumming  of  the  journal  transactions.  This will
              allow the recovery code in e2fsck and the kernel to detect  cor-
              ruption  in  the  kernel.  It is a compatible change and will be
              ignored by older kernels.

       journal_async_commit
              Commit block can be written to disk without waiting for descrip-
              tor  blocks.  If  enabled older kernels cannot mount the device.
              This will enable

       journal=update
              Update the ext4 filesystem’s journal to the current format.

       barrier=0 / barrier=1 / barrier / nobarrier
              This enables/disables the use of write barriers in the jbd code.
              barrier=0 disables, barrier=1 enables.  This also requires an IO
              stack which can support barriers, and if jbd gets an error on  a
              barrier write, it will disable again with a warning.  Write bar-
              riers enforce proper on-disk ordering of journal commits, making
              volatile  disk  write  caches  safe  to use, at some performance
              penalty.  If  your  disks  are  battery-backed  in  one  way  or
              another, disabling barriers may safely improve performance.  The
              mount options "barrier" and "nobarrier"  can  also  be  used  to
              enable  or  disable  barriers,  for  consistency with other ext4
              mount options.

              The ext4 filesystem enables write barriers by default.

       inode_readahead_blks=n
              This tuning parameter controls the maximum number of inode table
              blocks that ext4’s inode table readahead algorithm will pre-read
              into the buffer cache.  The value must be  a  power  of  2.  The
              default value is 32 blocks.

       stripe=n
              Number  of  filesystem  blocks  that mballoc will try to use for
              allocation size and alignment. For RAID5/6 systems  this  should
              be  the  number  of  data  disks * RAID chunk size in filesystem
              blocks.

       delalloc
              Deferring block allocation until write-out time.

       nodelalloc
              Disable delayed allocation. Blocks are allocation when  data  is
              copied from user to page cache.

       max_batch_time=usec
              Maximum  amount of time ext4 should wait for additional filesys-
              tem operations to be batch together  with  a  synchronous  write
              operation. Since a synchronous write operation is going to force
              a commit and then a wait for the I/O complete, it  doesn’t  cost
              much,  and  can  be  a  huge throughput win, we wait for a small
              amount of time to see if any other transactions can piggyback on
              the  synchronous  write. The algorithm used is designed to auto-
              matically tune for the speed  of  the  disk,  by  measuring  the
              amount of time (on average) that it takes to finish committing a
              transaction. Call this time the "commit time".  If the time that
              the  transactoin  has been running is less than the commit time,
              ext4 will try sleeping for the commit time to see if other oper-
              ations  will  join the transaction. The commit time is capped by
              the max_batch_time, which defaults to 15000us (15ms). This opti-
              mization can be turned off entirely by setting max_batch_time to
              0.
:
