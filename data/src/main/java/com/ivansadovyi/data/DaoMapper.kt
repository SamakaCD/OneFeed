package com.ivansadovyi.data

interface DaoMapper<DomainLayerType, DaoType> {

	fun mapFromDao(item: DaoType): DomainLayerType

	fun mapToDao(item: DomainLayerType): DaoType
}
