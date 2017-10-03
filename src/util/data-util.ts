import * as moment from 'moment';

export class DataUtil{

    static getDataFormatada(data: Date): string{
        return moment(data).format("DD/MM/YYYY");
    }

}