using BookingMaster.Data.Model;

namespace BookingMaster.Repositories
{
    public interface IRoomRepository
    {
        public Task<IEnumerable<Room>> GetRoomsByLocation(string Location);
    }
}
