java -Xmx250m \
    -cp ".:jbzip2-0.9.jar:cisd-jhdf5.jar:htsjdk.jar" \
    uk.ac.babraham.FastQC.FastQCApplication \
    "$@"