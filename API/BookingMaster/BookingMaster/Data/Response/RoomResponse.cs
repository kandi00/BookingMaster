using BookingMaster.Data.Model;

namespace BookingMaster.Data.Response
{
    public class RoomResponse
    {
        public IEnumerable<Room>? Rooms { get; set; }
        public string Message { get; set; }
        public int Code { get; set; }
        
    }
}
