using BookingMaster.Data.Response;

namespace BookingMaster.Services
{
    public interface IRoomService
    {
        public Task<RoomResponse> GetRoomsByLocation(string? Location);
    }
}
