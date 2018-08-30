package com.ivansadovyi.data

interface DaoMapper<DomainLayerType, DaoType> {

	fun mapToDomainLayerType(o: DaoType): DomainLayerType

	fun mapToDaoType(o: DomainLayerType): DaoType
}
