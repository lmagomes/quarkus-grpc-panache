package org.acme

import com.google.protobuf.Empty
import io.smallrye.common.annotation.Blocking
import io.smallrye.mutiny.Uni
import org.acme.models.Person
import org.acme.proto.GetPersonsReply
import org.acme.proto.MutinyPersonListGrpc
import javax.inject.Singleton
import javax.transaction.Transactional
import org.acme.proto.Person as ProtoPerson

@Singleton
class PersonResource : MutinyPersonListGrpc.PersonListImplBase() {

    @Override
    @Blocking
    @Transactional
    override fun getPersons(request: Empty?): Uni<GetPersonsReply> {

        val persons = Person.listAll().map {
            ProtoPerson.newBuilder().setName(it.name).build()
        }

        return Uni.createFrom().item {
            GetPersonsReply.newBuilder().addAllPerson(persons).build()
        }
    }
}
