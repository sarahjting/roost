// https://stackoverflow.com/a/18650828
// thank u l2aelba and stackoverflow community wiki
export const formatBytes = function (bytes: number, decimals = 2): string 
{
    if (!bytes)  {
        return '0 B'
    }

    const k = 1024
    const dm = decimals < 0 ? 0 : decimals
    const sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']

    const i = Math.floor(Math.log(bytes) / Math.log(k))

    return `${parseFloat((bytes / Math.pow(k, i)).toFixed(dm))} ${sizes[i]}`
}

// this formats a date from the database, which expects iso8601
const days = ['Sun', 'Mon','Tue', 'Wed', 'Thu','Fri', 'Sat'];
export const formatDate = function (date: string): string
{
    const today = new Date();
    const parsedDate = new Date(date + '+00:00');

    const timeString = `${parsedDate.getHours()}`.padStart(2, '0') + ':' + `${parsedDate.getMinutes()}`.padStart(2, '0');

    if (parsedDate.getFullYear() !== today.getFullYear()) {
        return `${parsedDate.getFullYear()}/${parsedDate.getMonth() + 1}/${parsedDate.getDate()} ${timeString}`
    } else {
        return `${parsedDate.getMonth() + 1}/${parsedDate.getDate()} (${days[parsedDate.getDay()]}) ${timeString}`
    }
}
